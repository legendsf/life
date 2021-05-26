package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeBU {

    public static void merge(int[] integers,int min,int mid,int max) {
        int i=min;
        int j=mid+1;
        int[] aux=new int[integers.length];
        for (int k = 0; k < integers.length; k++) {
            aux[k]=integers[k];
        }
        for(int k=min;k<=max;k++){
            if (i>mid) {
                integers[k]=aux[j++];
            }else if (j>max) {
                integers[k]=aux[i++];
            }else if (aux[i]<aux[j]) {
                integers[k]=aux[i++];
            }else {
                integers[k]=aux[j++];
            }
        }

    }
    /**
     * 自底向上归并排序
     * 外层for循环控制子序列长度
     * 内层for循环控制子序列合并
     * 归并排序算法复杂度O(N²)
     * @param integers
     * @return
     */
    public static int[] sort(int[] integers) {
        for (int i = 1; i < integers.length; i=i+i) {
            for(int j=0;j<integers.length-i;j=j+2*i){
                merge(integers, j, j+i-1, Math.min(j+2*i-1, integers.length-1));
            }
        }
        return integers;
    }



    //自底向上归并排序
    public static <T extends Comparable<? super T>> void mergeSortDownToUp(T[] A){
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) Array.newInstance(A.getClass().getComponentType(), A.length);
        int len,i,j,k,start,mid,end;
        //len表示归并子数组的长度，1表示，一个一个的归并，归并后的长度为2,2表示两个两个的归并，归并后的长度为4,以此类推
        for(len = 1; len < A.length; len = 2*len){
            //复制到辅助数组中
            System.arraycopy(A, 0, aux, 0, A.length);
            //按照len的长度归并回A数组，归并后长度翻倍
            for(start = 0; start < A.length; start = start+2*len){
                mid = start + len - 1;
                //对于数组长度不满足2的x次幂的数组，mid可能会大于end
                end = Math.min(start + 2*len - 1, A.length-1);
                i = start;
                //mid大于end时,j必然大于end,所以不会引起越界访问
                j = mid+1;
                //[start,mid] [mid+1, end]
                for(k = start; k <= end; k++){
                    if(i > mid){
                        A[k] = aux[j++];
                    }else
                    if(j > end){
                        A[k] = aux[i++];
                    }else
                    if(aux[i].compareTo(aux[j]) < 0){
                        A[k] = aux[i++];
                    }else{
                        A[k] = aux[j++];
                    }
                }
            }
        }
    }

    public static int[] sort1(int[] arr){
        for(int i=1;i<arr.length;i*=2){
           for(int j=0;j<arr.length;j+=2*i){
              merge1(arr,j,j+i-1,Math.min(j+2*i-1,arr.length-1));
            }
        }
        return arr;
    }

    public static void merge1(int[] arr,int min,int mid,int max){
       int i=min;
       int j=mid+1;
       int[]aux=new int[arr.length];
       System.arraycopy(arr,0,aux,0,arr.length);
       for(int k=min;k<=max;k++){
           if(i>mid){
               arr[k]=aux[j++];
           }else if(j>max){
               arr[k]=aux[i++];
           }else if(aux[i]<aux[j]){
               arr[k]=aux[i++];
           }else {
               arr[k]=aux[j++];
           }
       }
    }

    public static void main(String[] args) {
       int[] arr=  new int[]{3,9,5,5,5,5,7,1,4,8};
        Integer[] arr1=  new Integer[]{3,9,5,7,1,4,8};
        int[] integers=sort1(arr.clone());
        System.out.println(Arrays.toString(integers));
        mergeSortDownToUp(arr1);
        Arrays.stream(arr1).forEachOrdered(x->System.out.print(x+" "));
        System.out.println();

    }

}