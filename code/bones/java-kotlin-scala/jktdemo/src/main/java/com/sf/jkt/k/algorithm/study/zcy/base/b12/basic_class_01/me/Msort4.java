package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;

public class Msort4 {
    public static void main(String[] args) {
        checkSort(Msort4::bubbleSort);
        checkSort(Msort4::selectSort);
        checkSort(Msort4::insertSort);
    }

    public static int[] bubbleSort(int[] arr){
       for(int end=arr.length-1;end>0;end--){
           for(int i=0;i<end;i++){
               if(arr[i]>arr[i+1]){
                  swap(arr,i,i+1);
               }
           }
       }
       return arr;
    }

    public static int[] selectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++ ){
               minIndex=arr[j]>arr[minIndex]?minIndex:j;
            }
            swap(arr,minIndex,i);
        }
        return arr;
    }

    public static int[] insertSort(int[] arr){
       for(int i=1;i<arr.length;i++){
           for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
              swap(arr,j,j+1);
           }
       }
       return arr;
    }
}
