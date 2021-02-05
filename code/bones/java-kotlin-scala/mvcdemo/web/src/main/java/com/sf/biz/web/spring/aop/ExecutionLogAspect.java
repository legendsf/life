package com.sf.biz.web.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 例子举例：
 * https://blog.csdn.net/qq_23167527/article/details/78623639
 * https://blog.csdn.net/lmb55/article/details/82470388?ops_request_misc=%25257B%252522request%25255Fid%252522%25253A%252522161207710916780262520491%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D&request_id=161207710916780262520491&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-82470388.pc_search_result_no_baidu_js&utm_term=springboot+%25E6%2595%25B4%25E5%2590%2588+aop
 * https://www.cnblogs.com/loveLands/articles/9889712.html
 * https://segmentfault.com/a/1190000018120725
 */
@Component
@Aspect //切面，一类横切逻辑，比如日志切面，事务切面，hystrix 切面等等
@Slf4j
public class ExecutionLogAspect {
    //切点，拦截对应的拦截点，避免在所有被拦截方法上添加注解之类

    /**
     * controller及 controller子包下面所有的方法
     */
    @Pointcut("execution(* com.sf.biz.web.controller..*.*(..))")
    public void weblog(){

    }

    /**
     * controller及其子包下所有方法
     * .. 代表多个包
     * *代表任意一个字符
     * +代表类及其子类
     * 运算符：&&,||,!
     */
    @Pointcut("execution(* com.sf..controller..*.*(..))")
    public void weblog1(){}

    /**
     * controller 及其子包下所有方法,用这个更简洁
     */
    @Pointcut("execution(* com.sf..controller..*(..))")
    public void weblog2(){}

    /**
     * controller 及其子包下的 hello 方法
     */
    @Pointcut("execution(* com.sf..controller..hello(..))")
    public void weblog3(){}

    /**
     * AopController 的 hello 方法参数任意
     */
    @Pointcut("execution(* com.sf..controller..AopController.hello(..))")
    public void weblog4(){}

    /**
     * AopController 的 hello 方法一个参数
     */
    @Pointcut("execution(* com.sf..controller..AopController.hello(*))")
    public void weblog5(){}

    /**
     * AopController 的 hello 方法两个参数第一个int 第二个任意
     */
    @Pointcut("execution(* com.sf..controller..AopController.hello(int,*))")
    public void weblog6(){}

    /**
     * AopController 的 hello 方法两个参数第一个int 第二个String
     */
    @Pointcut("execution(* com.sf..controller..AopController.hello(int,String))")
    public void weblog7(){}

    /**
     * AopController 的 hello 方法两个参数第一个String  第二个String
     */
    @Pointcut("execution(* com.sf..controller..AopController.hello(String,String))")
    public void weblog8(){}

    /**
     * AopController 里面 thrwosException 的方法
     */
    @Pointcut("execution(* com.sf..controller..AopController.*(..)throws Exception)")
    public void weblog9(){}

    /**
     * 连接点：1.方法（selectById)2.位置（调用前，调用后）
     */
    @Before("weblog()")
    public void logbefore(){
        log.info("logbefore");
    }
    @Before("weblog1()")
    public void logbefore1(){
       log.info("logbefore1");
    }
    @Before("weblog2()")
    public void logbefore2(){
        log.info("logbefore2");
    }
    @Before("weblog3()")
    public void logbefore3(){
        log.info("logbefore3");
    }
    @Before("weblog4()")
    public void logbefore4(){
        log.info("logbefore4");
    }
    @Before("weblog5()")
    public void logbefore5(){
        log.info("logbefore5");
    }
    @Before("weblog6()")
    public void logbefore6(){
        log.info("logbefore6");
    }
    @Before("weblog7()")
    public void logbefore7(){
        log.info("logbefore7");
    }
    @Before("weblog8()")
    public void logbefore8(){
        log.info("logbefore8");
    }

    @Before("weblog9()")
    public void logbefore9(){
        log.info("logbefore9");
    }
}
