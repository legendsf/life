package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01;

import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;

public class Msort1 {
    public static void main(String[] args) {
//        checkSort(Msort1::bubbleSort);
//        checkSort(Msort1::selectionSort);
//        checkSort(Msort1::insertSort);
//        checkSort(Msort1::mergeSort);
        checkSmall(Msort1::minPlus);
    }

    public static int[] bubbleSort(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] > arr[minIndex] ? minIndex : j;
            }
            swap(arr, i, minIndex);
        }
        return arr;
    }

    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        return mergeSortProcess(arr,0,arr.length-1);
    }

    public static int[] mergeSortProcess(int[] arr,int L,int R) {
        if(L==R){
            return arr;
        }
        int mid=L+((R-L)>>1);
        mergeSortProcess(arr,L,mid);
        mergeSortProcess(arr,mid+1,R);
        merge(arr,L,mid,R);
        return arr;
    }

    public static int[] merge(int[] arr, int L, int Mid, int R) {
        int[] help = new int[R - L + 1];
        int i=0;
        int p0 = L;
        int p1 = Mid + 1;
        while (p0<=Mid&&p1<=R) {
           help[i++]=arr[p0]<arr[p1]?arr[p0++]:arr[p1++];
        }
        while (p0<=Mid){
            help[i++]=arr[p0++];
        }
        while (p1<=R){
            help[i++]=arr[p1++];
        }
        for(int j=0;j<help.length;j++){
            arr[L+j]=help[j];
        }
        return arr;
    }

    public static int minPlus(int[] arr){
       if(arr==null||arr.length<2){
           return 0;
       }
       return minPlusMergeSort(arr,0,arr.length-1);
    }

    public static int minPlusMergeSort(int[]arr,int l,int r){
        if(l==r){
            return 0;
        }
        int mid=l+((r-l)>>1);
        return minPlusMergeSort(arr,l,mid)
         +minPlusMergeSort(arr,mid+1,r)+
                minPlusMerge(arr,l,mid,r);
    }
    public static int minPlusMerge(int[] arr,int l,int mid,int r){
        int[] help = new int[r-l+1];
        int result=0;
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
            result += arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++] =arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
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
        return result;
    }

    public static int minPlusMerge1(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}
