package com.sf.biz.web.controller.aop;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

    @RequestMapping("/hello")
    public String hello(String name){
       return "hello: "+name;
    }
    @RequestMapping("/hello/noarg")
    public String hello(){
        return "hello noArgs";
    }
    @RequestMapping("/hello/twoarg")
    public String hello(int id,String name){
        return "hello id: "+id+" ; name "+name;
    }
    @RequestMapping("/hello/twostr")
    public String hello(String sex,String name){
        return "hello sex="+sex+";name="+name;
    }

    @RequestMapping("/except")
    public String except()throws Exception{
        return "exception";
    }
}
