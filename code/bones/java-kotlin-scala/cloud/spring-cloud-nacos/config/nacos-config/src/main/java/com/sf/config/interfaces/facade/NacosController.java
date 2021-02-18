package com.sf.config.interfaces.facade;

import com.sf.config.infrastructure.config.DataConfig;
import com.sf.config.infrastructure.config.Demo1Config;
import com.sf.config.infrastructure.config.DownlinkConfig;
import com.sf.config.infrastructure.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 * 单:我们为每个要捕获的外部属性提供一个带有字段的类。请注意以下几点:
 *
 * 前缀定义了哪些外部属性将绑定到类的字段上
 * 根据 Spring Boot 宽松的绑定规则，类的属性名称必须与外部属性的名称匹配
 * 我们可以简单地用一个值初始化一个字段来定义一个默认值
 * 类本身可以是包私有的
 * 类的字段必须有公共 setter 方法
 * Spring 宽松绑定规则 (relaxed binding)
 * Spring使用一些宽松的绑定属性规则。因此，以下变体都将绑定到 hostName 属性上
 */
@RefreshScope
@RestController
@RequestMapping("/nacos")
public class NacosController {

    @Autowired
    private DownlinkConfig downlinkService;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private Demo1Config demo1ConfigProperties;

    @Autowired
    private DataConfig dataConfig;

    @Value("${demo.user.name}")
    String userName;

    @Value("${demo.user.age}")
    int age;

    @GetMapping("/config")
    public String refreshConfig(){
        return "hello:"+userName+" | "+age+"|UserConfig:"+userConfig.name+"|"+userConfig.age;
    }

    @GetMapping("/demo1/msg")
    public String getMsg(){
       return "hello"+ demo1ConfigProperties.getPool()+"|"+dataConfig.getMsg();
    }

    @GetMapping("/pool")
    public Map<String, String> getPool(){
        return downlinkService.getPool();
    }

}
