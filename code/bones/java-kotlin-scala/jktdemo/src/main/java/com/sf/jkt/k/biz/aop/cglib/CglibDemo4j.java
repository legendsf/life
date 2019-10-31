package com.sf.jkt.k.biz.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDemo4j {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MSample.class);
        enhancer.setCallback(new CglibProxy4J());
        MSample sample = (MSample) enhancer.create();
        sample.test("hello");
    }
}

class CglibProxy4J implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method");
        Object result = null;
//        result = method.invoke(o, objects);//代理的 方法 会循环调用进入intercept
//        result=methodProxy.invoke(o,objects);
        result=methodProxy.invokeSuper(o,objects);
        System.out.println("after method");
        return result;
    }
}

class MSample {
    public void test(String msg) {
        System.out.println("hello: " + msg);
    }
}