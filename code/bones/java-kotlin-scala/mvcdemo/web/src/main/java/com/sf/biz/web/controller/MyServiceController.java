package com.sf.biz.web.controller;


import com.sf.biz.web.spring.aop.self.MySelfService;
import com.sf.biz.web.spring.aop.self.Myhello1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServiceController {
    @Autowired
    private Myhello1 myhello1impl;

    @Autowired
    private MySelfService mySelfService;

    @RequestMapping("/proxy")
    public String testproxy(){
        myhello1impl.hello1();
        System.out.println("-------------------");
        myhello1impl.hello2();
        System.out.println("-------------------");
        mySelfService.fun1();
        System.out.println("-------------------");
        mySelfService.fun2();
        return "testproxy";
    }
}
