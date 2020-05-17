package com.sf.jkt.j.spring.biz.classload.spi;

import org.springframework.stereotype.Component;

@Component
public class ConsolePrint implements Iprint {


    @Override
    public void print(String msg) {
        System.out.println("console print: " + msg);
    }

    @Override
    public boolean verify(Integer condition) {
        return condition <= 0;
    }
}
