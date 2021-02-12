package com.sf.ddd.application.service;

import com.sf.ddd.application.remote.ThirdpartyFeignClient;
import com.sf.ddd.domain.service.UserServiceImpl;
import com.sf.ddd.infrastructure.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAppService {
   @Resource
   ThirdpartyFeignClient thirdpartyFeignClient;

   @Resource
   UserServiceImpl userService;

   public void   helloAndCreateUser(){
      Util.hello();
      thirdpartyFeignClient.hello("Create User");
      userService.hello("userServiceImpl");
   }

}
