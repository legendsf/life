package com.sf.biz.web.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
@Aspect
@Slf4j
public class WithInLogAspect {

  /**
   * within 子类和父类都行，如果注解在父类，那么子类只有重写父类方法才有效果
   * within 是指定类型 @within 是注解类型
   * 连接点的横切点
   */
  @Pointcut("within(com.sf..RedisController)")
  public void pc1(){

  }
  /**
   * 连接点 方法和位置
   */
  @Before("pc1()")
  public void log1(){
    log.info("within log1");
  }

  @Pointcut("@within(com.sf.biz.web.spring.aop.example.A1)")
  public void pca1(){}
  @Pointcut("@within(com.sf.biz.web.spring.aop.example.A2)")
  public void pca2(){}





  /**
   *with 是
   */
  @Before("pca1()")
  public void withInA1(){
      log.info("within a1");
  }
  @Before("pca2()")
  public void withInA2(){

    log.info("within a2");
  }


}
