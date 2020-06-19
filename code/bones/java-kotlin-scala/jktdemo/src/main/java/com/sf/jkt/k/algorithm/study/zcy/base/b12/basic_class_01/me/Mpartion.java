package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

import java.util.Arrays;

public class Mpartion {
   public static int[] partion(int[] arr,int l,int r,int p){
      int less=l-1;
      int more=r+1;
      while (l<more){
         if(arr[l]<p){
            //小于区扩大一位，并和小于等于区域下个位置（arr[l]<p 的 l的位置）
            Constants.swap(arr,++less,l++);
         }else if(arr[l]>p){
            //大于等于区域扩大一位，并且和当前 l交换
            Constants.swap(arr,--more,l);
         }else {
            l++;//指针下移//小于区域没扩大
         }
      }
      return new int[]{less+1,more-1};//返回等与区域的左右两个点
   }

   public static void main(String[] args) {
       int[]arr=new int[]{3,7,1,5,4,9};
      Arrays.stream(partion(arr, 0, arr.length - 1, 5)).forEach(System.out::print);
      Constants.printArray(arr);
   }
}
