package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class InversePairs1 {

    public static void main(String[] args) {
        int[] arr=new int[]{4,3,5,2,6,1};
        System.out.println(Constants.inversePairs(arr));
        System.out.println(inversePairs(arr));
        Constants.checkInverse(InversePairs1::inversePairs);
    }

    public static int inversePairs(int[] arr){
      if(arr==null||arr.length<2){
          return 0;
      }
      return mergeSort(arr,0,arr.length-1);
    }

    public static int mergeSort(int[]arr,int l,int r){
        if(l==r){
            return 0;
        }
        int mid=l+((r-l)>>1);
        return mergeSort(arr,l,mid)+mergeSort(arr,mid+1,r)+merge(arr,l,mid,r);
    }

    public static int merge(int[]arr,int l,int mid,int r){
       int[] copy = new int[arr.length];
       System.arraycopy(arr,l,copy,l,r-l+1);
       int p1=mid;
       int p2=r;
       int count=0;
       int copyIndex=r;
       while (p1>=l&&p2>=mid+1){//从后往前
          if(arr[p1]>arr[p2]){
              count += p2-mid;
              copy[copyIndex--]=arr[p1--];
          }else {
              copy[copyIndex--]=arr[p2--];
          }
       }

       while (p1>=l){
           copy[copyIndex--]=arr[p1--];
       }
       while (p2>=mid+1){
          copy[copyIndex--]=arr[p2--];
       }
       for(int k=l;k<=r;k++) {
           arr[k]=copy[k];
       }
       return count;
    }

}
