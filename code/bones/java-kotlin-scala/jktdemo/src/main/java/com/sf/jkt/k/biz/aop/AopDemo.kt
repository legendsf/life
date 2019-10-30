package com.sf.jkt.k.biz.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.DeclareParents
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


interface Singer {
    fun sing()
}

class BackSinger() : Singer {
    override fun sing() {
        println("哈哈哈你们去哪里呀？！")
    }
}

interface Performance {
    fun perform()
}

@Component
class Dancer1() : Performance {
    override fun perform() {
        println("I am dancer one!")
    }
}

@Component
class Dancer2() : Performance {
    override fun perform() {
        println("I am dancer two!")
    }
}

@Aspect
@Component
class SingerIntroducer {
//    @DeclareParents(value = "com.sf.jkt.k.biz.aop.Performance+", defaultImpl = BackSinger::class)
//    lateinit var singer: Singer


    /***
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
    throws-pattern?)
     * 返回值是 String,
     * 类是biz包下的所有类
     * 方法是test
     * 参数是：第一个参数是String类型，后面还有其他0或者多个参数值
     * *匹配一个单词
     * ..如果是在类路径中是匹配所有子包
     * ..如果是在参数中是匹配所有0或者多个
     * args 参数名称一个是name 一个是password
     */
//    @Pointcut(value = "execution(* com.sf.jkt.k.biz..*.hello(String,..) && args(name,password))")
//    @Pointcut(value = "execution(* com.sf.jkt.k.biz..*.*(..))")
//    fun helloPointWithArgs(name: String, password: String) {
//        println("helloPointWithArgs:" + name + " " + password)
//    }

    /****
     * 不限定参数名称
     */
//    @Pointcut(value = "execution(* com.sf.jkt.k.biz..*.hello(..))")
//    @Pointcut(value = "execution(* *(*))")
//    fun helloPoint() {
//        println("helloPoint:")
//    }

    /****
     * 不限定参数名称
     */
//    @Pointcut(value = "execution(* com.sf.jkt.k.biz..*.hello(..) && args(String,java.util.Date))",
//            argNames = "good,luck")
//    @Pointcut(value = "execution(* com.sf.jkt.k.biz..*.*(..))")
//    fun helloPointWithArgNames(name: String, password: String) {
//        println("helloPointWithArgNames:" + name + " " + password)
//    }

//    @Before(value = "helloPoint")
//    fun before_helloPoint(joinPoint:JoinPoint) {
//        println("before_helloPoint:"+joinPoint.signature.declaringTypeName+"|"+joinPoint.signature.name)
//    }

//    @Before(value = "helloPointWithArgs(name,password)")
//    fun before_helloPointWithArgs(joinPoint:JoinPoint) {
//        println("before_helloPointWithArgs:"+joinPoint.signature.declaringTypeName+"|"+joinPoint.signature.name)
//    }

//    @Before(value = "helloPointWithArgNames(name,password)")
//    fun before_helloPointWithArgNames(joinPoint:JoinPoint) {
//        println("helloPointWithArgNames:"+joinPoint.signature.declaringTypeName+"|"+joinPoint.signature.name)
//    }

    @Before(value = "execution(* com.sf.jkt.k.biz.aop.*.*(..))")
    fun simpleBefore0() {
        println("SimpleBefore0**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz.aop.*.*(..))")
    fun simpleBefore() {
        println("SimpleBefore**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz.aop.*.*(..))")
    fun simpleBefore1() {
        println("SimpleBefore1**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.*(..))")
    fun simpleBefore2() {
        println("SimpleBefore2**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.hello1(..))")
    fun simpleBefore3() {
        println("SimpleBefore3**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.hello(..))")
    fun simpleBefore4() {
        println("SimpleBefore4**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.service1(..))")
    fun simpleService1() {
        println("SimpleService1**************")
    }

    @Before(value = "execution(* com.sf.jkt.k.biz..*.service2(..))")
    fun simpleService2() {
        println("SimpleService2**************")
    }
}

//@Controller
@RestController
class Controller {
    @Autowired
    lateinit var controller: com.sf.jkt.k.biz.aop.Controller
    @RequestMapping("/hello")
    fun hello() {
        println("hello:")
    }

    @RequestMapping("/hello1")
    fun hello1(): String {
        println(controller.javaClass)
        controller.hello(Date())
        hello("I-AM-MSG")
        hello("I-AM-NAME", "I-AM-PASSWORD")
        hello("I-AM-GOOD", Date())
        return "AopDemo-hello1"
    }

    fun hello(date: Date) {
        println("hello date:" + date)
    }

    fun hello(msg: String) {
        println("hello msg:" + msg)
    }

    fun hello(name: String, password: String) {
        println("hello name password:" + name + " " + password)
    }

    fun hello(good: String, luck: Date) {

        println("hello good luck:" + good + " " + luck)
    }


}

@Configuration
class AopConfig {
}