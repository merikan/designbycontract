package com.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gdata.data.youtube.VideoEntry;

public class YouTubeServiceTest {
	
	private YouTubeTestDataHelper helper = new YouTubeTestDataHelper();
	private YouTubeVideoService service = new SimpleYouTubeVideoService(helper.getApplication());	
	
	public void testUpload() throws Exception {		
		VideoEntry video = service.uploadVideo(helper.getFilename());
		assertNotNull(video);
	}
	
	@Test
	public void testMostViewed() throws Exception {
		String feedUrl = "http://gdata.youtube.com/feeds/api/standardfeeds/most_viewed";
		service.printVideoFeed(feedUrl, true);
	}
	

	
	
}
