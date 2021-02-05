package com.sf.biz.web.annotation;

import com.sf.biz.web.spring.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Configuration
@Retention(RetentionPolicy.RUNTIME)
//import 普通的class 导入的时候是 bean,import registar时候会执行 registerBeanDefinitions 方法
@Import(MyBeanDefinitionRegistrar.class)
public @interface MyScan {
    String value()default "";
}
