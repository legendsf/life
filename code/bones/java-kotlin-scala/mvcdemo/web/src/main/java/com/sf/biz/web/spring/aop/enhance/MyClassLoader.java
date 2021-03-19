package com.sf.biz.web.spring.aop.enhance;

import sun.misc.Launcher;

public class MyClassLoader {
    public static void main(String[] args) {
        ClassLoader sys=ClassLoader.getSystemClassLoader();
        Launcher.getLauncher();
        System.out.println(sys);
    }
}
