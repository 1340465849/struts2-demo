package com.bulain.mybatis.controller;

import java.util.List;

import com.bulain.common.Struts2TestCase;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserView;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class UserActionTest extends Struts2TestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserActionTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_users.xml");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public void testList() throws Exception {
		initServletMockObjects();
		request.setParameter("search.firstName", "first_name_page");
		request.setParameter("search.lastName", "last_name_page");
		
		ActionProxy proxy = getActionProxy("/user/list");
		UserAction userAction = (UserAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<UserView> listLogin = userAction.getListLogin();
		assertEquals(3, listLogin.size());
	}

	public void testNewn() throws Exception {
		initServletMockObjects();
		
		ActionProxy proxy = getActionProxy("/user/new");
		UserAction userAction = (UserAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		User user = userAction.getUser();
		assertNotNull(user);
	}

	public void testCreate() throws Exception {
		initServletMockObjects();
		request.setParameter("user.firstName", "first_name");
		request.setParameter("user.lastName", "last_name");
		
		ActionProxy proxy = getActionProxy("/user/create");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

	public void testShow() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "102");
		
		ActionProxy proxy = getActionProxy("/user/show");
		UserAction userAction = (UserAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		User user = userAction.getUser();
		assertNotNull(user);
		assertEquals("first_name_102", user.getFirstName());
		assertEquals("last_name_102", user.getLastName());
	}

	public void testEdit() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "102");
		
		ActionProxy proxy = getActionProxy("/user/edit");
		UserAction userAction = (UserAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		User user = userAction.getUser();
		assertNotNull(user);
		assertEquals("first_name_102", user.getFirstName());
		assertEquals("last_name_102", user.getLastName());
	}

	public void testUpdate() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "103");
		request.setParameter("user.firstName", "first_name_updated");
		request.setParameter("user.lastName", "last_name_updated");
		
		ActionProxy proxy = getActionProxy("/user/update");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

	public void testDestroy() throws Exception {
		initServletMockObjects();
		request.setParameter("id", "101");
		
		ActionProxy proxy = getActionProxy("/user/destroy");
		proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

}
