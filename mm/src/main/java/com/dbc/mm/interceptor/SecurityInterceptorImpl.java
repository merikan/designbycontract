package com.dbc.mm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dbc.mm.vo.SessionState;

public class SecurityInterceptorImpl extends HandlerInterceptorAdapter {

	public Logger logger = Logger.getLogger(SecurityInterceptorImpl.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (!uri.endsWith("/login/view") && (!uri.endsWith("/login/submit"))) {
			logger.info("Before handling the request");
			SessionState state = (SessionState) request.getSession().getAttribute(SessionState.SESSION_STATE);
			if (null == state) {
				response.sendRedirect("/mm/pages/login/view");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
