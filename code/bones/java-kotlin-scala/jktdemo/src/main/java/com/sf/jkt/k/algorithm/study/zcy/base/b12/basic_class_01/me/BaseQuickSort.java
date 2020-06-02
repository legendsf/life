package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;
import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;
public class BaseQuickSort {
    public static void main(String[] args) {
        checkSort(BaseQuickSort::quickSort);
    }

    public static void quickSort(int[]arr){
        baseQuickSort(arr,0,arr.length-1);
    }

    public static void   baseQuickSort(int[]arr,int l,int r){
        if(arr==null||arr.length<2){
            return;
        }
        if(l<r){
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);//随机快排
            int[] p =partion(arr,l,r);
            baseQuickSort(arr,l,p[0]-1);
            baseQuickSort(arr,p[1]+1,r);
        }
    }

    public static int[] partion(int[]arr,int l,int r){
       int less=l-1;
       int more=r;
       //先用最后一个 数做分区基准
       while (l<more){
           if(arr[l]<arr[r]){
                swap(arr,++less,l++);
           }else if(arr[l]>arr[r]) {
               swap(arr,--more,l);
           }else {
               l++;
           }
       }
       //再把最后一个数和 more 区域最前面的数交换，则刚好小于在左边大于在右边，等于在中间
       swap(arr,more,r);
       return new int[]{less+1,more};
    }
}
