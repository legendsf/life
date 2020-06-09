package com.sf.jkt.j.spring.biz.resttemplate.feign;

import com.sf.jkt.j.spring.biz.exception.controller.User;

import java.util.List;

public interface UserServiceFacade {

    List<User> findAllUserV2();
}
