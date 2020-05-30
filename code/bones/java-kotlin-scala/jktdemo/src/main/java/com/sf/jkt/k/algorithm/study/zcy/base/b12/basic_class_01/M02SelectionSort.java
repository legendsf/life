package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class M02SelectionSort {
    public static void main(String[] args) {
        Constants.checkSort(M02SelectionSort::selectionSort);
    }

    public static int[] selectionSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        for(int i=0;i<arr.length-1;i++){//0 - N-1
            int minIndex=i;
           for(int j=i+1;j<arr.length;j++){
               minIndex=arr[j]<arr[minIndex]?j:minIndex;
           }
           swap(arr,i,minIndex);
        }
        return arr;
    }

    public static void swap(int[] arr,int i,int j){
            if(arr==null||arr.length<i||arr.length<j){
                return;
            }
          arr[i]=arr[i]^arr[j];
          arr[j]=arr[i]^arr[j];
          arr[i]=arr[i]^arr[j];
    }
}
