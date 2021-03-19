package com.sf.biz.jdk.collections;

import java.util.HashMap;
import java.util.Map;

public class Mapdemo {

    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(11));
        Map<String,String> map= new HashMap<>();
        map.put("k","v");
        map.put("k1","v1");
        System.out.println(map.entrySet());
    }
}
