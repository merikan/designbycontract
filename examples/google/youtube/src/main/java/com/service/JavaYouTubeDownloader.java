package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class JavaYouTubeDownloader {

	public static String newline = System.getProperty("line.separator");
	private static final Logger log = Logger.getLogger(JavaYouTubeDownloader.class.getCanonicalName());
	private static final Level defaultLogLevelSelf = Level.FINER;
	private static final Level defaultLogLevel = Level.WARNING;
	private static final Logger rootlog = Logger.getLogger("");
	private static final String scheme = "http";
	private static final String host = "www.youtube.com";
	private static final Pattern commaPattern = Pattern.compile(",");
	private static final Pattern pipePattern = Pattern.compile("\\|");
	private static final char[] ILLEGAL_FILENAME_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|',
			'\"', ':' };

	private String videoId = null;
	private String outdir = ".";
	private int format = 18; // http://en.wikipedia.org/wiki/YouTube#Quality_and_codecs
	private String encoding = "UTF-8";
	private String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
	private String directoryName;

	private static void usage(String error) {
		if (error != null) {
			System.err.println("Error: " + error);
		}
		System.err.println("usage: JavaYoutubeDownload VIDEO_ID DESTINATION_DIRECTORY");
		System.exit(-1);
	}

	public JavaYouTubeDownloader(String videoId) {
		this.videoId = videoId;
	}

	public JavaYouTubeDownloader(String videoId, String directoryName) {
		this.videoId = videoId;
		this.directoryName = directoryName;
	}

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			usage("Missing video id. Extract from http://www.youtube.com/watch?v=VIDEO_ID");
		}

		JavaYouTubeDownloader downloader = null;
		
		if (args.length == 1) {
			downloader = new JavaYouTubeDownloader(args[0]);
		} else if (args.length == 2) {
			downloader = new JavaYouTubeDownloader(args[0], args[1]);
		}
		downloader.downloadVideo();
	}

	public void downloadVideo() {
		setupLogging();
		log.fine("Starting");
		try
		{
			play(videoId, format, encoding, userAgent);
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}

		log.fine("Finished");
	}

	private String getExtension(int format) {
		// TODO
		return "mp4";
	}

	private void play(String videoId, int format, String encoding, String userAgent) throws Exception
	{
	
		log.fine("Retrieving " + videoId);
		URI uri = getVideoInfoUrl(videoId, format);

		CookieStore cookieStore = new BasicCookieStore();
		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		httpget.setHeader("User-Agent", userAgent);

		log.finer("Executing " + uri);
		HttpResponse response = httpclient.execute(httpget, localContext);
		HttpEntity entity = response.getEntity();
		if (entity != null && response.getStatusLine().getStatusCode() == 200) {
			InputStream instream = entity.getContent();
			String videoInfo = getStringFromInputStream(encoding, instream);
			if (videoInfo != null && videoInfo.length() > 0) {
				
				VideoDetails details = getVideoDetails(videoInfo);
		
				File outputDir = new File(outdir);
				File outputfile = new File(outputDir, details.getFilename());

				if (details.getDownloadUrl() != null) {
					downloadWithHttpClient(userAgent, details.getDownloadUrl(), outputfile);
				}
			}
		}
	}

	private VideoDetails getVideoDetails(String videoInfo) {
		VideoDetails details = new VideoDetails();		
		
		List<NameValuePair> infoMap = new ArrayList<NameValuePair>();
		URLEncodedUtils.parse(infoMap, new Scanner(videoInfo), encoding);

		for (NameValuePair pair : infoMap) {
			String key = pair.getName();
			String val = pair.getValue();
			log.finest(key + "=" + val);
			if (key.equals("token")) {
				details.setToken(val);
			} else if (key.equals("title")) {
				details.setFilename(val);
			} else if (key.equals("fmt_url_map")) {
				String[] formats = commaPattern.split(val);
				for (String fmt : formats) {
					String[] fmtPieces = pipePattern.split(fmt);
					if (fmtPieces.length == 2) {
						// in the end, download somethin!
						details.setDownloadUrl(fmtPieces[1]);
						int pieceFormat = Integer.parseInt(fmtPieces[0]);
						if (pieceFormat == format) {
							// found what we want
							details.setDownloadUrl(fmtPieces[1]);
							break;
						}
					}
				}
			}
		}
		return details;
	}

	private URI getVideoInfoUrl(String videoId, int format) throws URISyntaxException {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("video_id", videoId));
		qparams.add(new BasicNameValuePair("fmt", "" + format));
		URI uri = getUri("get_video_info", qparams);
		return uri;
	}

	private void downloadWithHttpClient(String userAgent, String downloadUrl, File outputfile) throws Exception{
		HttpGet httpget2 = new HttpGet(downloadUrl);
		httpget2.setHeader("User-Agent", userAgent);

		log.finer("Executing " + httpget2.getURI());
		HttpClient httpclient2 = new DefaultHttpClient();
		HttpResponse response2 = httpclient2.execute(httpget2);
		HttpEntity entity2 = response2.getEntity();
		if (entity2 != null && response2.getStatusLine().getStatusCode() == 200) {
			long length = entity2.getContentLength();
			InputStream instream2 = entity2.getContent();
			log.finer("Writing " + length + " bytes to " + outputfile);
			if (outputfile.exists()) {
				outputfile.delete();
			}
			FileOutputStream outstream = new FileOutputStream(outputfile);
			try {
				byte[] buffer = new byte[2048];
				int count = -1;
				while ((count = instream2.read(buffer)) != -1) {
					outstream.write(buffer, 0, count);
				}
				outstream.flush();
			} finally {
				outstream.close();
			}
		}
	}

	private String cleanFilename(String filename) {
		for (char c : ILLEGAL_FILENAME_CHARACTERS) {
			filename = filename.replace(c, '_');
		}
		return filename;
	}

	private URI getUri(String path, List<NameValuePair> qparams) throws URISyntaxException {
		URI uri = URIUtils.createURI(scheme, host, -1, "/" + path, URLEncodedUtils.format(qparams, "UTF-8"), null);
		return uri;
	}

	private void setupLogging() {
		changeFormatter(new Formatter() {
			@Override
			public String format(LogRecord arg0) {
				return arg0.getMessage() + newline;
			}
		});
		explicitlySetAllLogging(Level.FINER);
	}

	private void changeFormatter(Formatter formatter) {
		Handler[] handlers = rootlog.getHandlers();
		for (Handler handler : handlers) {
			handler.setFormatter(formatter);
		}
	}

	private void explicitlySetAllLogging(Level level) {
		rootlog.setLevel(Level.ALL);
		for (Handler handler : rootlog.getHandlers()) {
			handler.setLevel(defaultLogLevelSelf);
		}
		log.setLevel(level);
		rootlog.setLevel(defaultLogLevel);
	}

	private String getStringFromInputStream(String encoding, InputStream instream) throws UnsupportedEncodingException, IOException {
		Writer writer = new StringWriter();

		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(instream, encoding));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			instream.close();
		}
		String result = writer.toString();
		return result;
	}

	private class VideoDetails
	{
		private String token;
		private String downloadUrl;
		private String filename;
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getDownloadUrl() {
			return downloadUrl;
		}
		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}		
		
		public String getFormattedFilename()
		{
			//File outputDir = new File(outdir);
			String extension = getExtension(format);

			filename = cleanFilename(filename);
			if (filename.length() == 0) {
				filename = videoId;
			} else {
				filename += "_" + videoId;
			}
			filename += "." + extension;
			return filename;
		}
	}
	
}


