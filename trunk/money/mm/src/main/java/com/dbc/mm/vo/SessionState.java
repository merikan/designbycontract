package com.dbc.mm.vo;

import com.dbc.mm.model.User;

public class SessionState {
	
	public static final String SESSION_STATE = "SESSION_STATE";
	private User loggedOnUser;
	
	private String sid = "asdasasdasd";
	private String screen = "logon";
	private Long releaseId = null;
	
	public User getLoggedOnUser() {
		return loggedOnUser;
	}
	public void setLoggedOnUser(User loggedOnUser) {
		this.loggedOnUser = loggedOnUser;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public static String getSessionState() {
		return SESSION_STATE;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public Long getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}

}
