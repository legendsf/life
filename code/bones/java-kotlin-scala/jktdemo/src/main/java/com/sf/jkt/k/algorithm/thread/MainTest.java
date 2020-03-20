package com.sf.jkt.k.algorithm.thread;

public class MainTest {
    public static void main(String[] args) {
        byte a = 127;

        byte b = 127;

//        b = a + b; // error : cannot convert from int to byte

        b += a; // ok
        System.out.println(b);
        System.out.println(3*0.1 == 0.3);
    }


}
