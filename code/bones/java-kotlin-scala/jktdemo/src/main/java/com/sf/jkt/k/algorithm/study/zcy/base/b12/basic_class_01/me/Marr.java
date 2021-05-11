package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.Arrays;

public class Marr {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5};
        Arrays.stream(Arrays.copyOfRange(arr, 0, 2)).forEach(System.out::println);
    }
}
