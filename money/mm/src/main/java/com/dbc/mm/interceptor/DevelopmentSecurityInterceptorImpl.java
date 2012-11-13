package com.dbc.mm.interceptor;

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

	private String DEFAULT_ID = "1";
	
	public Logger logger = Logger.getLogger(DevelopmentSecurityInterceptorImpl.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
	throws Exception {
		logger.info("Before handling the request");
		SessionState state = (SessionState) request.getSession().getAttribute(SessionState.SESSION_STATE);
		if (null == state)
		{
			String id = getUserFromRequest(request);
			if (id != null)
			{
				setUser(request, id);	
			}
			setUser(request, DEFAULT_ID);
		}
		return super.preHandle(request, response, handler);
	}

	private String getUserFromRequest(HttpServletRequest request) {
		String s = (String) request.getParameter("id");
		logger.debug("ID Found in Request" + s);
		return s;
	}

	private void setUser(HttpServletRequest request, String id) {
		
		id = id.toUpperCase();
		logger.info("Setting the user to " + id);
		User user = userService.findOne(new Long(id));
		if (user == null)
		{
			throw new RuntimeException("There is no user in system with the ID " + id);
		}
		SessionState state = new SessionState();
		state.setLoggedOnUser(user);
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

}
