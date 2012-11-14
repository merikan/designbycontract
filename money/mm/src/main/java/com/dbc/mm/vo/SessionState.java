package com.dbc.mm.vo;

import com.dbc.mm.model.Account;
import com.dbc.mm.model.User;

public class SessionState {
	
	public static final String SESSION_STATE = "SESSION_STATE";
	private User loggedOnUser;
	private Account account;
	
	
	
	public User getLoggedOnUser() {
		return loggedOnUser;
	}
	public void setLoggedOnUser(User loggedOnUser) {
		this.loggedOnUser = loggedOnUser;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
