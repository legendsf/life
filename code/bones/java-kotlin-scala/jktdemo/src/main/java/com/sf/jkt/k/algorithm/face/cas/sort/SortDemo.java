package com.sf.jkt.k.algorithm.face.cas.sort;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Collections;

public class SortDemo {

    public static void main(String[] args) {
//        testSort();
////        testMerge();
//        testQuickSort();
        testMergeSort();
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static void testMerge() {
        int[] a = new int[]{1, 3, 5, 7, 9};
        int[] b = new int[]{4, 8, 12};
        Arrays.stream(merge(a, b)).forEach(System.out::print);
    }

    public static void testMergeSort() {
        int[] arr = new int[]{5, 3, 9, 7, 1, 2};
        Arrays.stream(mergeSort(arr)).forEach(System.out::print);
    }

    public static void testQuickSort() {
        int[] arr = new int[]{5, 3, 9, 7, 1, 2};
        quickSort(arr, 0, arr.length - 1);
        Ints.asList(arr).stream().forEach(System.out::print);
    }

    public static void testSort() {
        int[] arr = new int[]{5, 3, 9, 7, 1, 2};
        int[] expected = new int[]{1, 2, 3, 5, 7, 9};
        int[] result = bubbleSort(arr);
        Ints.asList(result).stream().forEach(System.out::print);
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 从小到大的序列
     *
     * @param sa
     * @param sb
     * @return
     */
    public static int[] merge(int[] sa, int[] sb) {
        int[] sc = new int[sa.length + sb.length];
        int a = 0, b = 0;
        for (int i = 0; i < sc.length; i++) {
            if (a < sa.length && b < sb.length) {
                if (sa[a] > sb[b]) {
                    sc[i] = sb[b];
                    b++;
                } else {
                    sc[i] = sa[a];
                    a++;
                }
            } else if (a < sa.length) {
                sc[i] = sa[a];
                a++;
            } else if (b < sb.length) {
                sc[i] = sb[b];
                b++;
            }
        }
        return sc;
    }

    public static void quickSort(int[] num, int left, int right) {
        if (left >= right) {
            return;
        }
        int key = num[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (num[j] >= key && i < j) {
                j--;
            }
            while (num[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        quickSort(num, left, i - 1);
        quickSort(num, i + 1, right);
    }
}
