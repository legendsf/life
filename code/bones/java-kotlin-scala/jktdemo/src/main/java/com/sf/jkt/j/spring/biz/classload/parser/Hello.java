package com.sf.jkt.j.spring.biz.classload.parser;

public interface Hello {
    public String hello(String str);

    default String execute(String type, String msg) {
        return "";
    }

    ;
}
