package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;
import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;

public class Msort2 {
    public static void main(String[] args) {
//        checkSort(Msort2::bubbleSort);
//        checkSort(Msort2::selectionSort);
//        checkSort(Msort2::insertSort);
        checkSort(Msort2::mergeSort);
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
    public static int[] selectionSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                minIndex=arr[j]>arr[minIndex]?minIndex:j;
            }
            swap(arr,minIndex,i);
        }
        return arr;
    }

    public static int[] insertSort(int[] arr){
       for(int i=1;i<arr.length;i++){
           for(int j=i-1;j>=0;j--){
               if(arr[j]>arr[j+1]){
                  swap(arr,j,j+1);
               }
           }
       }
       return arr;
    }

    public static int[] mergeSort(int[] arr){
       if(arr==null||arr.length<2){
           return arr;
       }
       return sortProcess(arr,0,arr.length-1);
    }

    public static int[] sortProcess(int[]arr,int l,int r){
       if(l==r){
           return arr;
       }
       int mid=l+((r-l)>>1);
       sortProcess(arr,l,mid);
       sortProcess(arr,mid+1,r);
       merge(arr,l,mid,r);
       return arr;
    }

    public static int[] merge(int[] arr,int l,int mid,int r){
        int[] help = new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
           help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[l+j]=help[j];
        }
        return arr;
    }






















}
