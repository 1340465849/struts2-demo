package com.bulain.common.jsp;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionContext{
	private static final Map<String,HttpSession> SESSION_MAP = new LinkedHashMap<String,HttpSession>();
	
	private SessionContext(){}
	
	public static void addSession(HttpSession session){
		SESSION_MAP.put(session.getId(), session);
	}
	public static void removeSession(HttpSession session){
		SESSION_MAP.remove(session.getId());
	}
	public static HttpSession getSession(String sid){
		return SESSION_MAP.get(sid);
	}
}
