package com.sf.jkt.k.algorithm.algo.stack08;

public class Msort {

    public static void main(String[] args) {
        int result=0;
//         result= Math.subtractExact(Integer.MAX_VALUE,Integer.MIN_VALUE);
//        System.out.println(result);
//       result=  Math.multiplyExact(Integer.MAX_VALUE,Integer.MIN_VALUE);
//        result=Math.addExact(Integer.MAX_VALUE,Integer.MAX_VALUE);
        result=Math.floorDiv(Integer.MAX_VALUE,Integer.MIN_VALUE);
        System.out.println(result);
        result=Math.floorDiv(3,2);
        System.out.println(result);

    }

    public static void quickSort(int[] arr){
       if(arr==null||arr.length<2){
           return;
       }
       quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int l,int r){
        if(l<r){
           //partition
            //sort left
            //sort right
        }
    }

    public static int[] partition(int[] arr,int l,int r){//
return null;
    }

    public static void mergeSort(int[] arr){

    }

    public static void radiSort(int[] arr){

    }

    public static void radiSort2(int[] arr){

    }





}
