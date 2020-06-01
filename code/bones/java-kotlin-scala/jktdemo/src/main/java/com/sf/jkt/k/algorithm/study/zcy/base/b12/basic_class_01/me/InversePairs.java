package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class InversePairs {
    public static void main(String[] args) {
        testInversePairs(null);
    }
    public static void testInversePairs(int[] arr){
        arr=new int[]{4,3,5,2,6,1};
        System.out.println(Constants.inversePairs(arr));
        System.out.println(Constants.inversePairs1(arr));
    }
}
