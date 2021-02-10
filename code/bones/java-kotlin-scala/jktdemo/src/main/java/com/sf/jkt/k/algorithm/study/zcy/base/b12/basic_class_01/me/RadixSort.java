package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.Arrays;

/**
 * https://blog.csdn.net/weixin_46705768/article/details/109476877
 */
public class RadixSort {
    public static void main(String[] args) {
            int[] arr={755,54,82,4,10,3444,668,49};
//            radixSort(arr);
        MySort5.sortRadix(arr);
        System.out.println(Arrays.toString(arr));
    }
    /*
  基数排序（radix sort）属于“分配式排序”（distribution sort），
  又称“桶子法”（bucket sort）或 bin sort，顾
  名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
        思路: 找出要排序数组arr 中的最大值判断其位数,其位置就是代码循环的次数
        1.然后分别 以个位数判断数组中的每个数字 插入桶中
        2.再从桶中取出数据 放回原数组
        再循环判断十位 百位 等 重复1 2 步骤
     */

    public static void radixSort(int[] arr){
        if(arr==null||arr.length<2){return;}
        //先创建10个桶 每个桶的长度就是数组本身长度
        int[][] tong=new int[10][arr.length];

        //找出数组中的最大值 判断其位数 确定循环次数
        int max=arr[0];
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        int weishu=(max+"").length();//确定位数

        //当我们往桶中放完数据后,需要取出,定义长度为10的一维数组
        //记录对应桶中有效数值的个数
        int[]  bucketElementCounts=new  int[10];

        //循环原数组 不断向桶中放入 再根据每个桶中的有效值,将数据取出
        //放回原始数组,循环次数就是我们weishu
        for (int i = 0,n=1; i <weishu ; i++,n*=10) {//n 代表位数 个位 十位 百位等等
            for (int j = 0; j <arr.length ; j++) {//循环数组 根据位数 放入桶中
                int a=arr[j]/n%10;
                tong[a][bucketElementCounts[a]]=arr[j];//将数放入桶中
                bucketElementCounts[a]++;//该桶有效数值的个数+1
            }
            //以上循环结束 原数组的数据会都放在桶中,下面的思路就是把数据取出放回原数组
            int index=0;//作为记录原数组的下标
            for (int j = 0; j <tong.length ; j++) {//循环桶
                for (int k = 0; k <bucketElementCounts[j] ; k++) {//有效数值个数作为条件
                    arr[index++]=tong[j][k];
                }
                //当一个桶中的有效数值全部放回原数组后,要将该桶的有效数值个数归零即是
                bucketElementCounts[j]=0;
            }
        }
    }
}