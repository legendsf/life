package com.sf.biz.web.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect //切面，一类横切逻辑，比如日志切面，事务切面，hystrix 切面等等
@Slf4j
public class SelfAspect {
    @Pointcut("execution(* com.sf..self..*(..))")
    public void weblog2(){}

    @Before("weblog2()")
    public void logbefore(){
        log.info("SelfAspect-logbefore");
    }
}
