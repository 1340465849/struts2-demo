package com.bulain.mybatis.service;

import java.util.List;

import com.bulain.common.exception.ServiceException;
import com.bulain.common.page.Page;
import com.bulain.mybatis.dao.UserMapper;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserServiceImpl implements UserService{
	private UserMapper userMapper;
	
	public User get(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}
	public void insert(User user){
		int count = userMapper.insert(user);
		if(count!=1)throw new ServiceException();
	}
	public void update(User user, boolean forced){
		int count = 0;
		if(forced){
			count = userMapper.updateByPrimaryKey(user);
		}else{
			count = userMapper.updateByPrimaryKeySelective(user);
		}
		if(count!=1)throw new ServiceException();
	}
	public void delete(Integer id){
		int count = userMapper.deleteByPrimaryKey(id);
		if(count!=1) throw new ServiceException();
	}
	
	public List<User> find(UserSearch search){
		return userMapper.find(search);
	}
	public Long count(UserSearch search){
		return userMapper.count(search);
	}
	public List<User> page(UserSearch search, Page page){
		Long count = userMapper.count(search);
		page.setCount(count.longValue());
		search.setLow(page.getLow());
		search.setHigh(page.getHigh());
		return userMapper.page(search);
	}
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
