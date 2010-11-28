package com.bulain.mybatis.service;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public interface UserService {
	User get(Integer id);
	void insert(User user);
	void update(User user, boolean forced);
	void delete(Integer id);
	
	List<User> find(UserSearch search);
    Long count(UserSearch search);
    List<User> page(UserSearch search, Page page);
}
