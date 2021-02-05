package com.sf.biz.web.spring;


import com.sf.biz.web.mapper.BirdMapper;
import com.sf.biz.web.spring.proxy.BlueCar;
import com.sf.biz.web.spring.proxy.mybatis.MapperFactoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MyBeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        modifyBean(beanFactory);
        doProxy(beanFactory);
//        doMybatisMpper(beanFactory);

    }

    /**
     * Mybatis mapper 动态代理
     * @param beanFactory
     */
    public void doMybatisMpper(ConfigurableListableBeanFactory beanFactory){
        List<Class> mapperList=new ArrayList<>();
        mapperList.add(BirdMapper.class);
        for(Class mapper:mapperList){
            BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
            AbstractBeanDefinition bd= builder.getBeanDefinition();
            //spring 根据 bd 创建对象时，可以找到对应的构造参数，构造参数推断¡
            bd.getConstructorArgumentValues().addGenericArgumentValue(mapper);
            bd.setBeanClass(MapperFactoryBean.class);
        }
    }

    private void doProxy(ConfigurableListableBeanFactory beanFactory) {
        /**
         * 创建 bean 的方法
         * @Bean
         * FactoryBean.getObject（）// getObject("&user") 返回工厂类本身
         * beanFactory.registrySingleton()
         */
        //方式二：新增 beanDefinition
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
        AbstractBeanDefinition bd2=builder.getBeanDefinition();
        bd2.setBeanClass(BlueCar.class);
    }

    private void modifyBean(ConfigurableListableBeanFactory beanFactory) {
        //方式一：修改原有的bean 定义，比如注入一个代理类
        GenericBeanDefinition bd=(GenericBeanDefinition) beanFactory.getBeanDefinition("redCar");
//        bd.setBeanClass(UserA.class);
        bd.setBeanClass(BlueCar.class);
        MutablePropertyValues mvs= bd.getPropertyValues();
        mvs.addPropertyValue("carName","blueCar");

    }
}
