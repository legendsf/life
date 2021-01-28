package com.sf.jkt.j.spring.biz.spring;

import com.sf.jkt.j.spring.biz.spring.proxy.BlueCar;
import com.sf.jkt.j.spring.biz.spring.proxy.RedCar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
       //方式一：修改原有的bean 定义，比如注入一个代理类
        GenericBeanDefinition bd=(GenericBeanDefinition) beanFactory.getBeanDefinition("blackCar");
        bd.setBeanClass(RedCar.class);
        //方式二：新增 beanDefinition
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
        AbstractBeanDefinition bd2=builder.getBeanDefinition();
        bd2.setBeanClass(BlueCar.class);

    }
}
