package com.sf.jkt.j.spring.biz.comp.runtime;

public class RuntimeDemo {
    private int initFlag1;
    private volatile  int initFlag2;
    public static void main(String[] args) {
        run();
    }

    public static final void run(){
        //逻辑内核
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
