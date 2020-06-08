package com.sf.jkt.j.spring.biz.resttemplate.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * https://juejin.im/post/5d3e40ec51882551c37fc309
 *  1 prefix 必须小写
 *  2 必须有 setter 方法
 *  https://juejin.im/post/5d3e40ec51882551c37fc309
 *  validate:
 *  https://www.jianshu.com/p/ee7629944e87
 */

//@Validated
@Data
@Configuration
@ConfigurationProperties(prefix = "prefix.msg")
public class PrefixConfig {
    public String hello;
//    @Value("${prefix.msg.hello}")
//    public String hello1;

}
