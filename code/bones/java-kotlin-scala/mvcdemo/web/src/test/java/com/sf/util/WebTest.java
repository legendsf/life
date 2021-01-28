package com.sf.util;

import com.sf.base.ApplicationBaseTest;
import com.sf.biz.web.spring.proxy.BlueCar;
import com.sf.biz.web.spring.proxy.CarFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

public class WebTest extends ApplicationBaseTest implements BeanFactoryAware {
    /**
     * blueCar 上面没有 @component 注解，但是这里能够 autowired,原因postProcessor 里面把 redCar的 beandefinition
     * 修改成了 BlueCar
     */
    @Autowired
    BlueCar blueCar;

    @Autowired
    CarFactoryBean carFactoryBean;

    @Autowired
    private  BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }




    @Test
    public void testBeanFactoryPostProcessor(){
        System.out.println(blueCar.say());
        System.out.println(blueCar.getCarName());
    }
    @Test
    public void testCarFactoryBean()throws Exception{
        System.out.println(carFactoryBean.getObject());
        System.out.println(beanFactory.getBean("carFactoryBean"));
        //获取工厂类本身
        System.out.println(beanFactory.getBean("&carFactoryBean"));
    }
}
