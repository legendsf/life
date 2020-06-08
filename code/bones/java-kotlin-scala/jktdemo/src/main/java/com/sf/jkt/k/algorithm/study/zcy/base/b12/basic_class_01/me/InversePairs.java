package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.Arrays;

public class InversePairs {
    public static void main(String[] args) {
        testInversePairs(null);
    }
    public static void testInversePairs(int[] arr){
        arr=new int[]{4,3,5,2,6,1};
//        System.out.println(inversePairs(arr));
//        System.out.println(inversePairs1(arr));
//        System.out.println(inversePairs2(arr));
//        System.out.println();
//        Arrays.stream(arr).forEach(System.out::println);
//        System.out.println("*****");
//        checkInverse(InversePairs::inversePairs2);
        System.out.println( inversePairs2(arr));
        Arrays.stream(arr).forEach(System.out::println);

    }

    public static int inversePairs2(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int count = mergeSort(array, 0, array.length - 1);
        return count ;
    }

    private static int mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return 0;
        }

        // 找到数组的中点，分割为两个子数组，递归求解
        int middle = (start + end) / 2;
        int left = mergeSort(array, start, middle);
        int right = mergeSort(array, middle + 1, end);
        int count = merge(array, start, middle, end);

        return left + right + count;
    }

    private static int merge(int[] array, int start, int middle, int end) {
        // 存储归并后的数组
        int[] copy = new int[array.length];
        System.arraycopy(array, start, copy, start, end - start + 1);
        // 从两个子数组的尾部开始遍历
        int i = middle;
        int j = end;
        int copyIndex = end;
        // 记录逆序对的数量
        int count = 0;

        while (i >= start && j >= middle + 1) {
            // 数组是升序的
            // 如果左边数组比右边数组大，则将大的放入存储数组中
            // 并且累加逆序对，应为是有序的，所以左边数组的第i个元素比第j个及其之前的数都大
            if (array[i] > array[j]) {
                copy[copyIndex--] = array[i--];
                count += (j - middle);
            } else {
                copy[copyIndex--] = array[j--];
            }
        }

        // 将子数组剩余的部分一次写入归并后的存储数组
        while (i >= start) {
            copy[copyIndex--] = array[i--];
        }
        while (j >= middle + 1) {
            copy[copyIndex--] = array[j--];
        }

        // 将本次两个子数组的合并写入原数组中
        for (int k = start; k <= end ; k++) {
            array[k] = copy[k];
        }
        return count;
    }
}
