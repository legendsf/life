package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;
public class Mtest {
    public static void main(String[] args) {
//        checkSort(Mtest::bubbleSort);
//        checkSort(Mtest::selectionSort);
        checkSort(Mtest::insertSort);
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

    public static  int[] selectionSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                minIndex=arr[minIndex]>arr[j]?j:minIndex;
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
