package com.sf.jkt.j.spring.biz.resttemplate.feign;

import com.sf.jkt.j.spring.biz.exception.controller.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/userV2")
@RestController
public class UserControllerV2 implements UserFacade{
    @RequestMapping("/findAllUserV2")
    @Override
    public List<User> findAllUserV2() {
        List<User> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            User user=new User();
            user.id=Long.valueOf(i);
            user.account="account:"+i;
            user.password="password:"+i;
            user.email="email:"+i;
            list.add(user);
        };
        return list;
    }
}
