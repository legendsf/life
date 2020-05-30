package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class Msort {
    public  static int[] bubbleSort(int[] arr){
       if(arr==null||arr.length<2){
           return arr;
       }

       for(int end=arr.length-1;end>0;end--){
           for(int i=0;i<end;i++){
               if(arr[i]>arr[i+1]){
                   Constants.swap(arr,i,i+1);
               }
           }
       }

       return arr;
    }

    public static int[] selectionSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                minIndex=arr[minIndex]<arr[j]?minIndex:j;
            }
            Constants.swap(arr,i, minIndex);
        }
        return arr;
    }


        public static void main(String[] args) {
        int[] arr = new int[]{1,7,9,8};
        Constants.swap(arr,3,1);
//        Constants.printArray(arr);
//        Constants.checkSort(Msort::bubbleSort);

        }
}
