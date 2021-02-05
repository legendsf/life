package com.sf.biz.web.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * https://blog.csdn.net/demon7552003/article/details/97601209?ops_request_misc=%25257B%252522request%25255Fid%252522%25253A%252522161209341916780262534202%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D&request_id=161209341916780262534202&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-1-97601209.pc_search_result_no_baidu_js&utm_term=%2540within+%2540target%25E5%258C%25BA%25E5%2588%25AB
 *
 *
 * 相同点：
 * 对象的运行时绑定的方法所属的类必须与被@within或@target中的注解类型所注解的类是同一个类，方法拦截才生效。
 *
 * 运行时绑定的方法是指运行时对象动态绑定的方法，一般指override方法。
 *
 * @within,@target中的注解类型，本示例中指A1,A2
 *
 * 被A1注解的类有Human
 *
 * 被A2注解的类有Man。
 *
 *
 *
 * 不同点：
 * @target要求对象的运行时类型与被注解的类型是同一个类型
 * @within要求对象的运行时类型是被注解的类型的本身或者子类，子类必须 OVERRIDE
 */
@Component
@Aspect
@Slf4j
public class TargetLogAspect {
      @Pointcut("@target(com.sf.biz.web.spring.aop.example.A1)")
  public void pct1(){}
//  @Pointcut("@target(com.sf.biz.web.spring.aop.example.A2)")
//  public void pct2(){}

//    @Before("pct1()")
    public void targetA1() {

        log.info("target a1");
    }

//    @Before("pct2()")
//    public void targetA2() {
//
//        log.info("target a2");
//
//    }
}