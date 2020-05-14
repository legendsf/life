package com.sf.jkt.j.spring.biz.exception;

import org.springframework.stereotype.Component;

@Component
public class UserService {

   String addUser(User user){
      return "Success";
   }
}
