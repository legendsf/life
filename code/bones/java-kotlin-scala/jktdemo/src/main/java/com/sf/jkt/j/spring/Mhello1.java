package com.sf.jkt.j.spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mhello1 implements Hello {
    @Override
    public String hello(String str) {
        log.info("hello1:" + str);
        return "hello1:" + str;
    }
}
