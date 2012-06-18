package com.service;

import java.io.File;
import java.net.URL;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeMediaRating;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.data.youtube.YtStatistics;

public class SimpleYouTubeVideoService implements YouTubeVideoService {

	private static final String FILE_TYPE = "video/quicktime";
	private static final String UPLOAD_URL = "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";

	YouTubeService service;

	SimpleYouTubeVideoService(String application) {
		service = new YouTubeService(application);
	}

	SimpleYouTubeVideoService(String username, String googleId) {
		service = new YouTubeService(username, googleId);

	}

	
	public void printVideoFeed(String videoFeedUrl, boolean detailed) {

		try {
			VideoFeed videoFeed = service.getFeed(new URL(videoFeedUrl), VideoFeed.class);
			printVideoFeed(videoFeed, true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void printVideoFeed(VideoFeed videoFeed, boolean detailed) {
		for (VideoEntry videoEntry : videoFeed.getEntries()) {
			printVideoEntry(videoEntry, detailed);
		}
	}

	public VideoEntry uploadVideo(String filename) {

		VideoEntry newEntry = createVideoEntry();

		File videoFile = getVideo(filename, newEntry);
		MediaFileSource ms = new MediaFileSource(videoFile, FILE_TYPE);
		newEntry.setMediaSource(ms);

		try {
			VideoEntry createdEntry = service.insert(new URL(UPLOAD_URL), newEntry);
			return createdEntry;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void printVideoEntry(VideoEntry videoEntry, boolean detailed) {
		System.out.println("Title: " + videoEntry.getTitle().getPlainText());

		if (videoEntry.isDraft()) {
			System.out.println("Video is not live");
			YtPublicationState pubState = videoEntry.getPublicationState();
			if (pubState.getState() == YtPublicationState.State.PROCESSING) {
				System.out.println("Video is still being processed.");
			} else if (pubState.getState() == YtPublicationState.State.REJECTED) {
				System.out.print("Video has been rejected because: ");
				System.out.println(pubState.getDescription());
				System.out.print("For help visit: ");
				System.out.println(pubState.getHelpUrl());
			} else if (pubState.getState() == YtPublicationState.State.FAILED) {
				System.out.print("Video failed uploading because: ");
				System.out.println(pubState.getDescription());
				System.out.print("For help visit: ");
				System.out.println(pubState.getHelpUrl());
			}
		}

		if (videoEntry.getEditLink() != null) {
			System.out.println("Video is editable by current user.");
		}

		if (detailed) {

			YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();

			System.out.println("Uploaded by: " + mediaGroup.getUploader());

			System.out.println("Video ID: " + mediaGroup.getVideoId());
			System.out.println("Description: " + mediaGroup.getDescription().getPlainTextContent());

			MediaPlayer mediaPlayer = mediaGroup.getPlayer();
			System.out.println("Web Player URL: " + mediaPlayer.getUrl());
			MediaKeywords keywords = mediaGroup.getKeywords();
			System.out.print("Keywords: ");
			for (String keyword : keywords.getKeywords()) {
				System.out.print(keyword + ",");
			}

			GeoRssWhere location = videoEntry.getGeoCoordinates();
			if (location != null) {
				System.out.println("Latitude: " + location.getLatitude());
				System.out.println("Longitude: " + location.getLongitude());
			}

			Rating rating = videoEntry.getRating();
			if (rating != null) {
				System.out.println("Average rating: " + rating.getAverage());
			}

			YtStatistics stats = videoEntry.getStatistics();
			if (stats != null) {
				System.out.println("View count: " + stats.getViewCount());
			}
			System.out.println();

			System.out.println("\tThumbnails:");
			for (MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
				System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
				System.out.println("\t\tThumbnail Time Index: " + mediaThumbnail.getTime());
				System.out.println();
			}

			System.out.println("\tMedia:");
			for (YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
				System.out.println("\t\tMedia Location: " + mediaContent.getUrl());
				System.out.println("\t\tMedia Type: " + mediaContent.getType());
				System.out.println("\t\tDuration: " + mediaContent.getDuration());
				System.out.println();
			}

			for (YouTubeMediaRating mediaRating : mediaGroup.getYouTubeRatings()) {
				System.out.println("Video restricted in the following countries: " + mediaRating.getCountries().toString());
			}
		}
	}

	private File getVideo(String filename, VideoEntry newEntry) {
		File videoFile = new File(filename);
		if (!videoFile.exists()) {
			throw new RuntimeException("The file could not be found. " + filename);
		}
		return videoFile;
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
		/*
		 * mg.addCategory(new
		 * MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "mydevtag"));
		 * mg.addCategory(new
		 * MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME,
		 * "anotherdevtag"));
		 */
		newEntry.setGeoCoordinates(new GeoRssWhere(37.0, -122.0));
		// alternatively, one could specify just a descriptive string
		// newEntry.setLocation("Mountain View, CA");
		return newEntry;
	}

}
