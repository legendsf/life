package com.sf.biz.web.spring.aop.self;

import org.springframework.stereotype.Service;

@Service
public class Myhello1impl implements Myhello1{
    @Override
    public String hello1() {
        System.out.println("Myhello1impl.hello1");
        hello2();
        return "hello1";
    }

    @Override
    public String hello2() {
        System.out.println("Myhello1impl.hello2");
        return "hello2";
    }
}
