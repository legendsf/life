package com.sf.jkt.k.algorithm.algo.array;

import java.util.Arrays;

class Solution {
    static  int[]arr=new int[]{5,2,3,1};
    public static void main(String[] args) {
        Arrays.stream(sortArray(arr)).forEach(System.out::println);
    }
    public static int[] sortArray(int[] nums) {
        mergeSort(nums);
        return nums;
    }
    public static void mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }

        mergeSort(arr,0,arr.length-1,new int[arr.length]);
    }

    public static void mergeSort(int[] arr,int l,int r,int[]helper){
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        mergeSort(arr,l,mid,helper);
        mergeSort(arr,mid+1,r,helper);
        merge(arr,l,mid,r,helper);
    }

    public static void merge(int[] arr,int l,int mid ,int r,int[] helper){
        int i=0;
        int p1=l;
        int p2=mid+1;
        while(p1<=mid&&p2<=r){
            helper[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=mid){
            helper[i++]=arr[p1++];
        }
        while(p2<=r){
            helper[i++]=arr[p2++];
        }
        for(i=0;i<(r-l+1);i++){
            arr[l+i]=helper[i];
        }
    }



}