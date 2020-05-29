package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01;

import static basic_class_01.Code_00_BubbleSort.*;

public class M00BubbleSort {
    public static void main(String[] args) throws Exception{
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    public static int[] bubbleSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        for(int end=arr.length-1;end>0;end--){
            for(int i=0;i<end;i++){
               if(arr[i]>arr[i+1]){
                   swap(arr,i,i+1);
               }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}
