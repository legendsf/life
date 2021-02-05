package com.sf.util;

public class TestMain {
    public static void main(String[] args) {
        int b=4;
        for (int i = 0; i <10 ; i++) {
            if(i>b){
                continue;
            }
            if(i%2==0){
                System.out.println("eve");
            }else {
                System.out.println("odd");
            }
        }
    }
}
