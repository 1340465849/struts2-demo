package com.bulain.mybatis.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserMapperTest extends ServiceTestCase {
	@Autowired
    private UserMapper userMapper;
	
	@BeforeTransaction
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_users.xml");
	}

	@AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}
	
	@Test
	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = userMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	@Test
	public void testInsert() {
		User record = new User();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		int insert = userMapper.insert(record);
		assertEquals(1, insert);
	}

	@Test
	public void testInsertSelective() {
		User record = new User();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		int insert = userMapper.insertSelective(record);
		assertEquals(1, insert);
	}

	@Test
	public void testSelectByPrimaryKey() {
		User selectByPrimaryKey = userMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertNotNull(selectByPrimaryKey);
		
		assertEquals("first_name_102", selectByPrimaryKey.getFirstName());
		assertEquals("last_name_102", selectByPrimaryKey.getLastName());
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		User record = new User();
		record.setId(Integer.valueOf(103));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		User record = new User();
		record.setId(Integer.valueOf(104));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		int updateByPrimaryKey = userMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	@Test
	public void testFind(){
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<User> find = userMapper.find(search);
		assertEquals(3, find.size());
	}
	
	@Test
	public void testShoudExecuteWhenNoParam(){
		UserSearch search = new UserSearch();
		List<User> find = userMapper.find(search);
		assertEquals(7, find.size());
	}
	
	@Test
	public void testShoudExecuteWhenFirstNameIsNull(){
		UserSearch search = new UserSearch();
		search.setLastName("last_name_page");
		List<User> find = userMapper.find(search);
		assertEquals(3, find.size());
	}
	
	@Test
	public void testShoudExecuteWhenFirstNameIsNullStr(){
		UserSearch search = new UserSearch();
		search.setFirstName("");
		search.setLastName("last_name_page");
		List<User> find = userMapper.find(search);
		assertEquals(3, find.size());
	}
	
	@Test
	public void testShoudExecuteWhenLastNameIsNull(){
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		List<User> find = userMapper.find(search);
		assertEquals(3, find.size());
	}
	
	@Test
	public void testShoudExecuteWhenLastNameIsNullStr(){
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		List<User> find = userMapper.find(search);
		assertEquals(3, find.size());
	}
	
	@Test
	public void testCount(){
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = userMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}
	
	@Test
	public void testPage(){
		UserSearch search = new UserSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		search.setLow(0);
		search.setHigh(20);
		List<User> listUser = userMapper.page(search);
		assertEquals(3, listUser.size());
	}
}
