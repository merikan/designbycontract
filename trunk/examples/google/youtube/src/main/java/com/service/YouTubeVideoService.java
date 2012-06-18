package com.service;

import com.google.gdata.data.youtube.VideoEntry;

public interface YouTubeVideoService {
	public VideoEntry uploadVideo(String filename);
	public void printVideoFeed(String videoFeedUrl, boolean detailed);
}
