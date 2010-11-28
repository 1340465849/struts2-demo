package com.bulain.mybatis.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserServiceImplTest extends ServiceTestCase {
	private UserService userService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_users.xml");
		userService = (UserService) applicationContext.getBean("userService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(UserServiceImplTest.class);
	}
	public void testGet() {
		User user = userService.get(Integer.valueOf(102));
		assertNotNull(user);
		
		assertEquals("first_name_102", user.getFirstName());
		assertEquals("last_name_102", user.getLastName());
	}

	public void testInsert() {
		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		userService.insert(user);
	}

	public void testUpdate() {
		User user = new User();
		user.setId(Integer.valueOf(104));
		user.setFirstName("firstName-updated");
		user.setLastName("lastName-updated");
		userService.update(user, true);
	}

	public void testDelete() {
		userService.delete(Integer.valueOf(101));
	}

	public void testFind() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<User> find = userService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = userService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Page page = new Page();
		List<User> listUser = userService.page(search, page );
		assertEquals(3, listUser.size());
	}
}
