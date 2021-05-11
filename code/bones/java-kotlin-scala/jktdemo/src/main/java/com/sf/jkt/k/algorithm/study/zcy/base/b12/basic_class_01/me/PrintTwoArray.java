package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

public class PrintTwoArray {
    static int[] arr1=new int[]{1,2,4,6,7};
    static int[] arr2= new int[]{3,4,6,9};

    public static void main(String[] args) {
        printNotIn(arr1,arr2);
        printIn(arr1,arr2);
    }

    //打印arr2 中 不在 arr1中的数
    public static void printNotIn(int[]arr1,int[] arr2){
        int len1=arr1.length;
        int len2=arr2.length;
        int i=0;
        int j=0;
        while(i<len1&&j<len2){
           if(arr2[j]<=arr1[i]){
               if(arr2[j]<arr1[i]){
                   System.out.println(arr2[j]);
               }
               j++;
           }else {
               i++;
           }
        }
        while (j<len2){
            System.out.println(arr2[j++]);
        }
    }
    //打印arr2 中 在 arr1中的数

    public static void printIn(int[] arr1,int[] arr2){
        if(arr1==null||arr2==null||arr1.length<1||arr2.length<1){
            return;
        }
        int p1=0;
        int p2=0;
        int len1=arr1.length;
        int len2=arr2.length;
        while (p1<len1&&p2<len2){
            if(arr2[p2]<=arr1[p1]){
               if(arr2[p2]==arr1[p1]){
                   System.out.println(arr2[p2]);
               }
               p2++;
            }else {
                p1++;
            }
        }
    }

}
