package com.sf.jkt.j.spring.biz.exception.controller;

import com.sf.jkt.j.spring.biz.resttemplate.configuration.Person;
import com.sf.jkt.j.spring.biz.resttemplate.configuration.PrefixConfig;
import com.sf.jkt.j.spring.biz.resttemplate.configuration.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://blog.csdn.net/caidingnu/article/details/100885261
 */

@RestController
public class MytestController {
    @Autowired
    PrefixConfig prefixConfig;

//    @Value("${prefix.msg.hello}")
    String helloMsg="hello";
 
    @Autowired
    Person person;
 
    @Autowired
    Student student;
    
    @Value("${server.port}")
    private String port;
    
    @Value("#{123}")
    private int arg;
 
 
    @RequestMapping("/")
    Object contextLoads() {
        System.out.println(person);
        String hello1="";
//        hello1=prefixConfig.hello1;
        return  this.person+"--"+this.student+port+arg+prefixConfig.hello+"helloMsg:"+helloMsg+"|hello1:"+hello1;
    }
}


