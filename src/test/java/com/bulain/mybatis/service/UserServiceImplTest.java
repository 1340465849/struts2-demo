package com.bulain.mybatis.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

@DataSet(file="test-data/init_users.xml")
public class UserServiceImplTest extends ServiceTestCase {
    @Autowired
    @Qualifier("userServiceTx")
	private UserService userService;
	
    @Test
    public void testGet() {
		User user = userService.get(Long.valueOf(102));
		assertNotNull(user);
		
		assertEquals("first_name_102", user.getFirstName());
		assertEquals("last_name_102", user.getLastName());
	}

    @Test
	public void testInsert() {
		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		userService.insert(user);
	}
    
    @Test
	public void testUpdate() {
		User user = new User();
		user.setId(Long.valueOf(104));
		user.setFirstName("firstName-updated");
		user.setLastName("lastName-updated");
		userService.update(user, true);
	}

    @Test
	public void testDelete() {
		userService.delete(Long.valueOf(101));
	}

    @Test
	public void testFind() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<User> find = userService.find(search);
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = userService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Page page = new Page();
		List<User> listUser = userService.page(search, page );
		assertEquals(3, listUser.size());
	}
}
