package com.sf.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;

@RestController
public class TestRest {
    @RequestMapping("/test/{info}")
    public String test(@PathVariable(value = "info")String info)throws Exception{
        FileOutputStream fos = new FileOutputStream(new File("/usr/test/test.txt"));
        fos.write(info.getBytes());
        fos.close();
        return "test ok";
    }
}
