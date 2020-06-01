package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.Arrays;

public class TestConstant {
    public static void main(String[] args) {

    }
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for(int i=0;i<arr.length;i++){
           arr[i]=(int)((maxValue+1)*Math.random())-(int)(maxValue*Math.random());
        }
        return arr;
    }

    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    public static int[] copyArray(int[] arr){
        if(arr==null){
            return null;
        }
        int[] res = new int[arr.length];
        for(int i=0;i<res.length;i++){
            res[i]=arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1,int[] arr2){
        if(arr1==null&&arr2==null){
            return true;
        }
        if((arr1!=null&&arr2==null)||(arr1==null&&arr2!=null)){
            return false;
        }
        if(arr1.length!=arr2.length){
            return false;
        }
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }



}
