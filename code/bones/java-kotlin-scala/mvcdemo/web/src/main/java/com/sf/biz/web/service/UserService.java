package com.sf.biz.web.service;

import com.sf.biz.web.entity.User;
import com.sf.biz.web.mapper.UserDynamicSqlSupport;
import com.sf.biz.web.mapper.UserMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.findAll();
    }
    @Cacheable
    public User selectById(Integer id){
        return userMapper.selectById(id);
    }

    public List<User> selectByIds(List<Integer> ids){
        return userMapper.selectByIds(ids);
    }
    public List<User> selectByNames(List<String> names){
        return userMapper.selectByNames(names);
    }
    public User selectByUser(User user){
        return userMapper.selectByUser(user);
    }

    public User selectByUser2(User user){
        return userMapper.selectByUser2(user);
    }

    @Cacheable(key="#p0")
    public User selectByCacheId(int id){
        return this.selectById(id);
    }
}
