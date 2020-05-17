package com.sf.jkt.j.spring.biz.classload.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Component
public class MhelloFactory implements FactoryBean<Hello>, ApplicationContextAware, InitializingBean, Observer {

    ApplicationContext context;

    private boolean isNeddReload = true;

    ConcurrentHashMap<String, Hello> helloMap = new ConcurrentHashMap<>();
    private ReentrantReadWriteLock lock;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("********MhelloFactory" + context.getClass());
        System.out.println("********MhelloFactory" + context.getClass().getClassLoader());
//        registerInstances();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public boolean isNeddReload() {
        return isNeddReload;
    }

    public void setNeddReload(boolean neddReload) {
        isNeddReload = neddReload;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /***
     * 第二种，动态生成的代理类 HelloProxy$
     *
     * @return
     * @throws Exception
     */
    @Override
    public Hello getObject() throws Exception {

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Hello hello = helloMap.get((String) args[0]);
                return method.invoke(hello, args);
            }
        };
        return (Hello) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Hello.class}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return Hello.class;
    }

    @Override
    public void update(Observable o, Object arg) {
        //add class
        // new MyclassLoader.loadClass,put into map
        //update class
        //new MyclassLoader.loadClass, put into map, replace by key
        //remove class
        // remove by key;
        //rigster instance into map
    }

    public void registerInstances() {
        Lock writeLock = lock.writeLock();
        try {
            //write
            MyClassLoader1 cl = new MyClassLoader1();
            Class<?> mhelloClz = cl.findClass("Mhello");
            Class<?> mhelloClz1 = cl.findClass("Mhello1");
            Hello mhelloObj = (Hello) mhelloClz.newInstance();
            Hello mhelloObj1 = (Hello) mhelloClz1.newInstance();
            helloMap.put("000", mhelloObj);
            helloMap.put("001", mhelloObj1);
        } catch (Exception e) {
            log.error("registerInstances exception", e);
        } finally {
            writeLock.unlock();
        }
    }

    /***
     * 第一种调用方法，
     * 不启用FactoryBean,不用spring 管理bean
     * 直接自定义调用
     *
     * @param type
     * @param msg
     * @return
     */
    public String hello(String type, String msg) {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return helloMap.get(type).hello(msg);
        } finally {
            readLock.unlock();
        }
    }
}
