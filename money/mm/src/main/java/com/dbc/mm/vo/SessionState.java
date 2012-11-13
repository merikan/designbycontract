package com.dbc.mm.vo;

import com.dbc.mm.model.User;

public class SessionState {
	
	public static final String SESSION_STATE = "SESSION_STATE";
	private User loggedOnUser;
	
	
	public User getLoggedOnUser() {
		return loggedOnUser;
	}
	public void setLoggedOnUser(User loggedOnUser) {
		this.loggedOnUser = loggedOnUser;
	}
	
}
