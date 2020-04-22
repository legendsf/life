package com.sf.jkt.j.spring.parser;

public interface Hello {
    public String hello(String str);

    default String execute(String type, String msg) {
        return "";
    }

    ;
}
