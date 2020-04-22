package com.sf.jkt.j.spring;

public class Mhello implements Hello {

    public String message = "Mhello";

    @Override
    public String hello(String str) {
        str = "hello:" + str;
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        new Mhello().hello("mhello");
    }
}