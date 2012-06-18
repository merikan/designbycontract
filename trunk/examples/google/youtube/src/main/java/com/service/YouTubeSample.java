package com.service;

import java.io.File;
import java.net.URL;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;

public class YouTubeSample {

	String UPLOAD_URL = "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";
	
	public VideoEntry uploadVideo() {

		//YouTubeService service = new YouTubeService("sampleuser@gmail.com", "fakegoogleapplicationidjsuttoshowthatimgivingidhere");
		YouTubeService service = new YouTubeService("My Application");
		VideoEntry newEntry = createVideoEntry();

		MediaFileSource ms = new MediaFileSource(new File("D:\\maths.mp4"),	"video/quicktime");
		newEntry.setMediaSource(ms);

		try
		{
			VideoEntry createdEntry = service.insert(new URL(UPLOAD_URL), newEntry);
			return createdEntry;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private VideoEntry createVideoEntry() {
		VideoEntry newEntry = new VideoEntry();

		YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
		mg.setTitle(new MediaTitle());
		mg.getTitle().setPlainTextContent("My Test Movie");
		mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "Autos"));
		mg.setKeywords(new MediaKeywords());
		mg.getKeywords().addKeyword("cars");
		mg.getKeywords().addKeyword("funny");
		mg.setDescription(new MediaDescription());
		mg.getDescription().setPlainTextContent("My description");
		mg.setPrivate(false);
	/*	mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME,
				"mydevtag"));
		mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME,
				"anotherdevtag"));
*/
		newEntry.setGeoCoordinates(new GeoRssWhere(37.0, -122.0));
		// alternatively, one could specify just a descriptive string
		// newEntry.setLocation("Mountain View, CA");
		return newEntry;
	}
}
