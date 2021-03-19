package com.sf.util;

import com.sf.Application;
import com.sf.biz.web.mapper.BirdMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUtil {

    @Test
    public void testResource(){
        System.out.println(TestUtil.class.getResource("").getPath());
        System.out.println(TestUtil.class.getResource("/").getPath());
    }

    @Test
    public void testCtx(){
        System.out.println("hello");
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
        System.out.println(context.getBean("redCar"));
    }
    @Test
    public void testClass(){
        System.out.println(BirdMapper.class.getName());
        System.out.println(BirdMapper.class.getSimpleName());
        System.out.println(BirdMapper.class.getCanonicalName());
        System.out.println(StringUtils.capitalize("aaa"));
        System.out.println(StringUtils.uncapitalize("AAa"));
        System.out.println(StringUtils.lowerCase("AaA"));
    }

}
