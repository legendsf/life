package com.sf.biz.web.spring.proxy.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperFactoryBean implements FactoryBean {
    Class mapper;

    public MapperFactoryBean(Class mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object getObject() throws Exception {
        // Mapper代理对象
        Object o= Proxy.newProxyInstance(MapperFactoryBean.class.getClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(Object.class.equals(method.getDeclaringClass())){
                    //Object 的 equals hashcode toString 等方法
                    return method.invoke(this,args);
                }
                System.out.println(method.getName());
                return null;
            }
        });
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return mapper;
    }
}
