package com.sf.biz.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *  https://www.cnblogs.com/liu2-/p/9118393.html
 * bootdevtools
 * 1 bootmavenplugin 要配置 fork
 * 2 要用 maven 插件 spring-boot:run 来执行
 * 3 要保存下类文件触发重新编译 vim :w
 * 4 原理是maven 重新 运行一下，相当于应用重启
 */
@RestController
public class Hello {
    @RequestMapping("/")
    public String hello(){
        return "hello112";
    }
}
