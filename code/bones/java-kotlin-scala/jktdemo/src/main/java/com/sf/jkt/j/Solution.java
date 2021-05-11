package com.sf.jkt.j;

public class Solution {
    public static void main(String[] args) {
        System.out.println( is2n(11));
    }

    public static boolean  is2n(int n){
        return  (n&(n-1))==0;
    }
}

