package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

public class Msmall {
    public static void main(String[] args) {
//        checkSmall(Msmall::small);
          int[]arr=new int[]{1,7,3,8,2};
          int r= small(arr);
        System.out.println(r);
    }

    public static int small(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            res += arr[p2] > arr[p1] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return res;
    }

    public static int nixuMerge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int result = 0;
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
//            result += arr[p1] > arr[p2] ? (mid-p1+1) : 0;
            result += arr[p1]<arr[p2]? (r-p2+1)*arr[p1]:0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return result;
    }

}
