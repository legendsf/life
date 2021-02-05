package com.sf.biz.web.config;

import com.sf.biz.web.annotation.MyScan;
import com.sf.biz.web.spring.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.sf")
@MyScan("com.sf.biz.web.mapper")
public class AppConfig {
}
