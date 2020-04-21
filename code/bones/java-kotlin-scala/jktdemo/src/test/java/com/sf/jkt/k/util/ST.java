package com.sf.jkt.k.util;

import com.sf.jkt.k.web.KotlinApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class ST extends SpringBootBaseTest{
    @Autowired
    ApplicationContext context;
    @Test
    public void tsN(){
        System.out.println("AAAAAAAAAAhello");
        testLoadBean();
        KotlinApplication kt=(KotlinApplication) context.getBean("kotlinApplication");
        ClassLoader classLoader=        kt.getClass().getClassLoader();
        ClassLoader cl2=KotlinApplication.class.getClassLoader();

        System.out.println("AAAAAAAAAAhello");
    }

    public void testLoadBean(){
        String[] names= context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }

}
