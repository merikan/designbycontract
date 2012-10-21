package com.dbc.mm.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dbc.mm.model.User;
import com.dbc.mm.service.user.UserService;
import com.dbc.mm.vo.SessionState;

// Purely for development.
public class DevelopmentSecurityInterceptorImpl extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	private String defaultSid;
	
	public Logger logger = Logger.getLogger(DevelopmentSecurityInterceptorImpl.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
	throws Exception {
		logger.info("Before handling the request");
		SessionState state = (SessionState) request.getSession().getAttribute(SessionState.SESSION_STATE);
		if (null == state)
		{
			String requestSid = getUserFromRequest(request);
			if (requestSid != null)
			{
				setUser(request, requestSid);	
			}
			setUser(request, defaultSid);
		}
		return super.preHandle(request, response, handler);
	}

	private String getUserFromRequest(HttpServletRequest request) {
		String s = (String) request.getParameter("sid");
		logger.debug("SID Found in Request" + s);
		return s;
	}

	private void setUser(HttpServletRequest request, String sid) {
		sid = sid.toUpperCase();
		logger.info("Setting the user to " + sid);
		List<User> user = userService.getAllUsers();
		if (user == null)
		{
			throw new RuntimeException("There is no user in system with the SID " + sid);
		}
		SessionState state = new SessionState();
		state.setLoggedOnUser(user.get(0));
		request.getSession().setAttribute(SessionState.SESSION_STATE, state);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	throws Exception {
		logger.info("After handling the request");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
	throws Exception {
		logger.info("After rendering the view");
		super.afterCompletion(request, response, handler, ex);
	}

	public String getDefaultSid() {
		return defaultSid;
	}

	public void setDefaultSid(String defaultSid) {
		this.defaultSid = defaultSid;
	}
}
