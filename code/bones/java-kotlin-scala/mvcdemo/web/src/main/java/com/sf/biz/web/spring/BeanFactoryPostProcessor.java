package com.sf.biz.web.spring;


import com.sf.biz.web.spring.proxy.BlueCar;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        modifyBean(beanFactory);
        doProxy(beanFactory);

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
