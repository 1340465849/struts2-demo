package com.bulain.mybatis.dao;

import java.util.List;

import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //custom
    List<User> find(UserSearch search);
    Long count(UserSearch search);
    List<User> page(UserSearch search);
}