package com.sf.jkt.k.biz.aop.cglib

import org.springframework.cglib.proxy.Enhancer
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method


open class Sample {
    fun test(msg: String) {
        println("I-am-sample-test:" + msg)
    }
}

/***
 * obj: 代理后的对象是原始对象子类
 * method:原始对象方法
 * args:原始对象参数
 * 直接调用method.invoke(obj,args)，会形成嵌套调用
 * proxy 代理对象的包装的操作类 fastclass 机制
 */
class CglibLogInterceptor() : MethodInterceptor {
    override fun intercept(obj: Any, method: Method, args: Array<out Any>, proxy: MethodProxy): Any? {
        println("" + obj.javaClass.toString() + "|" + method.toString() + "|" + args.toString() + "|" + proxy.toString())
        println("before invocation")
        var result = Any()
        result = method.invoke(obj, args)
//         result = proxy.invokeSuper(obj, args)
        println("after invocation")
        return result
    }
}

fun main() {
    var enhancer = Enhancer()
    enhancer.setSuperclass(Sample::class.java)
    enhancer.setCallback(CglibLogInterceptor())
    var mproxy = enhancer.create()
    (mproxy as Sample).test("hello")
}