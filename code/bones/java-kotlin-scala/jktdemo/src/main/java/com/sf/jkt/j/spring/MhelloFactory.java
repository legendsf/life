package com.sf.jkt.j.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class MhelloFactory implements FactoryBean<Hello>, ApplicationContextAware, InitializingBean, Observer {

    ApplicationContext context;

    private boolean isNeddReload = true;

    ConcurrentHashMap<String, Hello> helloMap = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("********MhelloFactory" + context.getClass());
        System.out.println("********MhelloFactory" + context.getClass().getClassLoader());
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

    @Override
    public Hello getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        //add class
        //update class
        //remove class

    }

    public String hello(String type, String msg) {
        return helloMap.get(type).hello(msg);
    }
}
