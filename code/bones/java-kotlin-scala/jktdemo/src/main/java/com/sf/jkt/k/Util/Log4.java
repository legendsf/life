package com.sf.jkt.k.Util;

public class Log4 {
    private static final String name="log4";
    public static void info(Object msg){
        System.out.println(msg.toString());
        System.out.println("*********\\65279****************");
    }

    public static void main(String[] args) {
        System.out.println("*********\b****************");
        System.out.println("*********\r****************");
        System.out.println("*********\u200b****************");
        System.out.println("*********\u0011****************");
        System.out.println("*********\u0FD1****************");
    }
}
