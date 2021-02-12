package com.sf.ddd.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{
    public String hello(String msg){
        log.info("hello:"+msg);
        return "hello:"+msg;
    }
}
