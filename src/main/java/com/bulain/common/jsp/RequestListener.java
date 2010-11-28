package com.bulain.common.jsp;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestListener implements ServletRequestListener {
	private static final Logger LOG = LoggerFactory.getLogger(RequestListener.class);
	
	public void requestInitialized(ServletRequestEvent event) {
		LOG.debug("requestInitialized(ServletRequestEvent)-- start");
		
		HttpServletRequest request = (HttpServletRequest)event.getServletRequest();
		HttpSession session = request.getSession();
		SessionContext.addSession(session);
		
		LOG.debug("requestInitialized(ServletRequestEvent)-- end");
	}
	public void requestDestroyed(ServletRequestEvent event) {
		LOG.debug("requestDestroyed(ServletRequestEvent)-- start");
		
		LOG.debug("requestDestroyed(ServletRequestEvent)-- end");
	}
}
