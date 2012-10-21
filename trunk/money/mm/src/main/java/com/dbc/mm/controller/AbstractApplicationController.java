package com.dbc.mm.controller;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.dbc.mm.model.User;
import com.dbc.mm.vo.SessionState;

public class AbstractApplicationController {	
	
	public class UserContextHolder {
	    public SessionState currentSecurityContext() {
	        return (SessionState) 
	            RequestContextHolder.currentRequestAttributes()
	            .getAttribute(SessionState.SESSION_STATE, RequestAttributes.SCOPE_SESSION);
	    }
	    
	    public void setSessionState(User user)
	    {
			SessionState state = new SessionState();
			state.setLoggedOnUser(user);
            RequestContextHolder.getRequestAttributes().setAttribute(SessionState.SESSION_STATE, state, RequestAttributes.SCOPE_SESSION);
	    }
	}
	
}
