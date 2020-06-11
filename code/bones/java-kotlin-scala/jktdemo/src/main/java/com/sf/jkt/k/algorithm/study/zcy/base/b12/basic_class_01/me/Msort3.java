package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;

public class Msort3 {
    public static void main(String[] args) {
        checkSort(Msort3::bubbleSort);
        checkSort(Msort3::selectSort);
        checkSort(Msort3::insertSort);
    }
    public static int[] bubbleSort(int[] arr){
        if(arr==null||arr.length<2){
            return null;
        }
        for(int e=arr.length-1;e>0;e--){
            for(int j=0;j<e;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
        return arr;
    }

    public static int[] selectSort(int[] arr){

        if(arr==null||arr.length<2){
            return arr;
        }
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i;j<arr.length;j++){
                minIndex=arr[j]>arr[minIndex]?minIndex:j;
            }
            swap(arr,minIndex,i);
        }
        return arr;
    }

    public static int[] insertSort(int[] arr){
        if(arr==null||arr.length<2){

            return arr;
        }
        for(int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
        return arr;
    }

}
