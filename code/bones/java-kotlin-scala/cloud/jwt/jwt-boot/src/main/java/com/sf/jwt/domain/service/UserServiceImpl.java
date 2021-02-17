package com.sf.jwt.domain.service;

import com.sf.jwt.domain.entity.User;
import com.sf.jwt.domain.repository.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
   @Autowired
    UserMapper userMapper;

    public User getUser(String name,String password){
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
            return null;
        }
        return userMapper.selectByNameAndPassword(name,password);
    }
}
