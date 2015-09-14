package ru.sovzond.mgis2.web;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 * <p/>
 * http://stackoverflow.com/questions/4964145/detect-session-timeout-in-ajax-request-in-spring-mvc
 */
public class ExpiredSessionFilter extends GenericFilterBean {

	static final String FILTER_APPLIED = "__spring_security_expired_session_filter_applied";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getAttribute(FILTER_APPLIED) != null) {
			chain.doFilter(request, response);
			return;
		}

		request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
		if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SESSION_TIMED_OUT");
			return;
		}

		chain.doFilter(request, response);
	}
}
