package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

import java.util.Arrays;

/**
 * 快排
 * 归并
 * 顶向下
 * 下往上
 * 堆排序
 */
public class SortDemo {
    //partition 左中右，再递归左中右，l>r 终止
    public static int[] sortQuick(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        sortQuick(arr, 0, arr.length - 1);
        return arr;
    }

    public static void sortQuick(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] eq = partition(arr, l, r);
            sortQuick(arr, l, eq[0] - 1);
            sortQuick(arr, eq[1] + 1, r);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public static int[] sortMerge(int[] arr) {
        if (arr == null || arr.length < 1) {
            return arr;
        }
        sortMerge(arr, 0, arr.length - 1);
        return arr;
    }

    public static void sortMerge(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            sortMerge(arr, l, mid);
            sortMerge(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            help[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            help[k++] = arr[i++];
        }
        while (j <= r) {
            help[k++] = arr[j++];
        }
        for (k = 0; k < help.length; k++) {
            arr[l + k] = help[k];
        }
    }

    public static int[] sortHeap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        //构建大根堆
        for (int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int size=arr.length;
        swap(arr,0,--size);
        //放到末尾和保持
        while (size>0){
          heapify(arr,0,size);
          swap(arr,0,--size);
        }
        return arr;
    }

    public static void heapInsert(int[]arr,int i){
        while (arr[(i-1)/2]<arr[i]){
            swap(arr,(i-1)/2,i);
            i=(i-1)/2;
        }
    }

    public static void heapify(int[]arr,int index,int size){
        int left=2*index+1;
        while (left<size){
            int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;
            largest=arr[index]<arr[largest]?largest:index;
            if (largest==index){
                break;
            }
            swap(arr,index,largest);
            index=largest;
            left=2*index+1;
        }
    }

    public static int[]  sortMerge1(int[] arr){
        if (arr==null||arr.length<2){
            return arr;
        }
        int len=arr.length;
        for (int sz=1;sz<len;sz=2*sz){
            for (int lo=0;lo<len-sz;lo+=2*sz){
               merge(arr,lo,lo+sz-1,Math.min(lo+2*sz-1,len-1));
            }
        }
        return arr;
    }

    public static int[] sortMerge2(int[] arr){
        if (arr==null||arr.length<2){
            return arr;
        }
        int len=arr.length;
        for (int sz=1;sz<len;sz<<=1){
            for (int lo=0;lo<len-sz;lo+=2*sz){
               merge(arr,lo,lo+sz-1,Math.min(lo+2*sz-1,len-1));
            }
        }
        return arr;
    }



    public static void test1() {
        int[] arr = {1, 9, 3, 8, 5, 7};
        sortQuick(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sortMerge(new int[]{1, 9, 3, 8, 5, 7})));
        System.out.println(Arrays.toString(sortMerge1(new int[]{1, 9, 3, 8, 5, 7})));
        System.out.println(Arrays.toString(sortHeap(new int[]{1, 9, 3, 8, 5, 7})));
    }

    public static void main(String[] args) {
        test1();
    }
}
