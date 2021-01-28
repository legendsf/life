package com.sf.util;

import com.sf.Application;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUtil {
    public static void main(String[] args) {
        System.out.println("hello");
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
        System.out.println(context.getBean("redCar"));
    }
}
