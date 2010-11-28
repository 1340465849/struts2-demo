package com.bulain.common.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;
import com.bulain.common.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public abstract class PageSupportActionSupport extends DefaultActionSupport implements Preparable{
	private static final long serialVersionUID = 2368901952701997841L;
	private static final Logger LOG = LoggerFactory.getLogger(PageSupportActionSupport.class);

	protected Page page;
	private OrderBy orderBy;
	
	public void prepare() throws Exception {}
	
	protected Page getPageFromSession(){
		if(page==null) page = (Page) getBeanFromSession(Page.class);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	protected Search getSearchFromSession(Class clz, Search search){
		if(orderBy!=null)page = new Page();
		if(search!=null){
			orderBy = new OrderBy();
			page = new Page();
		}
		if(orderBy==null) orderBy = (OrderBy)getBeanFromSession(OrderBy.class);
		
		if(search==null) search = (Search) getBeanFromSession(clz);
		
		search.setOrderBy(orderBy.getOrderBy());
		search.setSequance(orderBy.getSequance());
		
		return search;
	}
	
	protected void putPageToSession(){
		putBeanToSession(Page.class, page);
	}
	
	@SuppressWarnings("unchecked")
	protected void putSearchToSession(Class clz, Search bean){
		putBeanToSession(clz, bean);
		putBeanToSession(OrderBy.class, orderBy);
	}
	
	
	@SuppressWarnings("unchecked")
	protected Object getBeanFromSession(Class clz){
		String key = Util.getSessionKey(this.getClass(), clz);
		Map session = ActionContext.getContext().getSession();
		
		Object bean = null;
		if(bean==null) bean = session.get(key);
		if(bean==null) 
		try {
			Constructor constructor = clz.getConstructor();
			bean =  constructor.newInstance();
		} catch (SecurityException e) {
			LOG.error("getBeanFromSession(Class)", e);
		} catch (NoSuchMethodException e) {
			LOG.error("getBeanFromSession(Class)", e);
		} catch (IllegalArgumentException e) {
			LOG.error("getBeanFromSession(Class)", e);
		} catch (InstantiationException e) {
			LOG.error("getBeanFromSession(Class)", e);
		} catch (IllegalAccessException e) {
			LOG.error("getBeanFromSession(Class)", e);
		} catch (InvocationTargetException e) {
			LOG.error("getBeanFromSession(Class)", e);
		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	protected void putBeanToSession(Class clz, Object bean){
		String key = Util.getSessionKey(this.getClass(), clz);
		Map session = ActionContext.getContext().getSession();
		session.put(key, bean);
	}

	protected String getLanguage(){
		return ActionContext.getContext().getLocale().getLanguage();
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public OrderBy getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
