package com.sf.biz.web.spring.aop.enhance;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

public class CglibLearn {


    public static Object getProxyInstance(Object realSubject) {
        Enhancer enhancer = new Enhancer();
        //需要创建子类的类,即定义委托类
        enhancer.setSuperclass(realSubject.getClass());
        //设置两个CallBack以及CallbackFilter
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new CglibProxy();
        callbacks[1] = new CglibProxy2();
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new filter());
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    public static void main(String[] args) {
        //将sam,class文件写到硬盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".//");
        //通过生成子类的方式创建代理类
        serviceImpl impl = (serviceImpl) getProxyInstance(new serviceImpl());
//        impl.say();
        System.out.println("*************");
        impl.toString();
    }
}
