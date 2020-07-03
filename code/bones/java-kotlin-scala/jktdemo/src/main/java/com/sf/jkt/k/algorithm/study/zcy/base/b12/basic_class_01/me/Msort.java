package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

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


    public static void heapSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int size=arr.length;
        Constants.swap(arr,0,--size);
        while (size>0){
            heapify(arr,0,size);
            Constants.swap(arr,0,--size);
        }
    }

    public static void heapInsert(int[] arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            Constants.swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }

    public static void heapify(int[] arr,int index,int size){
        int left=index*2+1;
        while (left<size){
            int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;
            largest=arr[largest]>arr[index]?largest:index;
            if(largest==index){
                break;
            }
            Constants.swap(arr,index,largest);
            index=largest;
            left=index*2+1;
        }
    }







        public static void main(String[] args) {
        int[] arr = new int[]{1,7,9,8};
        Constants.swap(arr,3,1);
//        Constants.printArray(arr);
//        Constants.checkSort(Msort::bubbleSort);
            Constants.checkSort(Msort::heapSort);
        }
}
