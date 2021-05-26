package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

import java.util.Arrays;
/**
 * @author evasean www.cnblogs.com/evasean/
 */
@SuppressWarnings("rawtypes")
public class Merge归并排序 {
    private static Comparable[] aux;
    private static int num=1;
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        System.out.println("merge lo="+lo+",mid="+mid+",hi="+hi);
        int i = lo;  //左半边元素索引记录
        int j = mid+1; //右半边元素索引记录
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for(int k = lo; k <= hi; k++){
            if(i > mid) {
                a[k] =  aux[j++];//左半边用尽，取右半边元素
            } else if(j > hi) {
                a[k] = aux[i++];//右半边用尽，取左半边元素
            } else if(less(aux[j],aux[i])) {
                a[k] = aux[j++];//右半边当前元素小于左半边当前元素，取右半边元素
            } else {
                a[k] = aux[i++];//右半边当前元素大于或等于左半边当前元素，取左半边元素
            }
        }
        System.out.println("第"+num+"次归并结果："+Arrays.toString(a));
        num++;
    }
    /**
     * 自顶向下的归并排序
     * @param a
     */
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    public static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) {
            return;
        }
        int mid = lo + (hi-lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    /**
     * 自底向上的归并排序
     * @param a
     */
    public static void sortBU(Comparable[] a){

        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N; sz=2*sz){
            for(int lo = 0; lo < N - sz; lo += 2*sz){
                merge(a,lo,lo+sz-1,Math.min(lo+2*sz-1, N-1));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable[] arr={1 ,9,3,8,5,7};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}