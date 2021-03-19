package com.sf.biz.web.spring.aop.self;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySelfService {
    @Autowired
    MySelfService mySelfService;//注入一个代理对象

    public void fun1(){
        System.out.println("MySelfService.fun1*****");
//        fun2();//用的被代理对象的方法
        mySelfService.fun2();//用的是代理对象的方法
    }

    public void fun2(){
        System.out.println("MySelfService.fun2*****");
    }
}
