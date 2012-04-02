package com.bulain.mybatis.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.mybatis.dao.UserMapper;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserServiceImpl extends PagedServiceImpl<User, UserSearch> implements UserService {
    private UserMapper userMapper;

    protected PagedMapper<User, UserSearch> getPagedMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
}
