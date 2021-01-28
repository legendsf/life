package com.sf.biz.web.spring.proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * 获取实例 carFactoryBean.getObject("carFactoryBean")
 */
@Component
public class CarFactoryBean implements FactoryBean {
    @Autowired
    private BlackCar blackCar;
    @Override
    public Object getObject() throws Exception {
        //返回一个动态代理拦截 car的实例
        Car car=(Car) Proxy.newProxyInstance(CarFactoryBean.class.getClassLoader(), new Class[]{Car.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                return method.invoke(blackCar,args);
            }
        });
//        return new BlueCar();
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
