package com.sf.jkt.k.biz.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AopController {

    @Autowired
    public NestedService nestedService;

    @RequestMapping("/log")
    public String log() {
        log.trace("************trace");
        log.debug("*************debug");
        log.info("*************info");
        log.warn("**************warn");
        log.error("************error");
        return "log";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        hello33();
        nestedService.service1();
        return "hello2";
    }

    @RequestMapping("/hello33")
    public String hello33() {
        System.out.println("java-hello");
        return "hello33";
    }

}

@Aspect
@Component
class MyAspect {
    @Before(value = "execution(* com.sf.jkt.k.biz..*.hello2(..))")
    public void simpleBefore5() {
        System.out.println("SimpleBefore5**************");
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.hello33(..))")
    public void simpleBefore6() {
        System.out.println("SimpleBefore6**************");
    }
}
