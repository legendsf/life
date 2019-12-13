package com.sf.jkt.k.comp.connection.netty;

import java.util.HashMap;

public class Jnetty {
    public static void main(String[] args) {
        HashMap map=new HashMap();
        map.put("key","value1");

        int len=15;
        int hash=106078;
        System.out.println(hash & len );
        System.out.println(hash % 16);

        int x = 1 & 8;
        System.out.println(x);
        x = 1 & 8 - 1;
        System.out.println(x);
        x = 1 & (8 - 1);
        System.out.println(x);
        x = (1 & 8) - 1;
        System.out.println(x);
        x = 0 & (8 - 1);
        System.out.println(x);
        System.out.println(5%(2^4-1));
        System.out.println(5&(2^4-1));
        System.out.println(Integer.toBinaryString(3));
    }
}
