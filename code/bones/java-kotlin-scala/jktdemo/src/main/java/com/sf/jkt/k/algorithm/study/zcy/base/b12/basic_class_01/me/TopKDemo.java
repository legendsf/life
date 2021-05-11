package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopKDemo {

    public  static void  mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }
    public static void mergeSort(int[] arr,int l,int r){
       if(l<r){
           int mid=l+((r-l)>>l);
           mergeSort(arr,l,mid);
           mergeSort(arr,mid+1,r);
           merge(arr,l,mid,r);
       }
    }
    public static void merge(int[] arr,int l,int mid,int r){
        int[] help=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
           help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(i=0;i<help.length;i++){
            arr[l+i]=help[i];
        }
    }

    public  static void quickSort(int[] arr){
       if(arr==null||arr.length<2){
           return;
       }
       quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int l,int r){
        if(l>=r){
            return;
        }
        swap(arr,l+(int)(Math.random()*(r-l+1)),r);
        int[] p=partition(arr,l,r);
        quickSort(arr,l,p[0]-1);
        quickSort(arr,p[1]+1,r);
    }

    public static int[] partition(int[] arr,int l,int r){
        int less=l-1;
        int more=r;
        while (l<more){
            if(arr[l]<arr[r]){
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,more,r);
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(5);
        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        queue.offer(1);

        queue.offer(8);
        queue.stream().forEach(System.out::println);
        System.out.println("sssss");
        System.out.println(queue.poll());
        queue.stream().forEach(System.out::println);



    }

    public  static  List<Integer> findTopK(int[] input,int k){
        List<Integer> list=new ArrayList<>();
        if(input==null||input.length<1){
            return list;
        }
        PriorityQueue<Integer>queue=new PriorityQueue<>(k,(o1,o2)->o1-o2);
        for(int num:input){
            if(queue.size()<k){
                queue.offer(num);
            }else if(queue.peek()<num){
                queue.poll();
                queue.offer(num);
            }

        }
        list.addAll(queue.stream().collect(Collectors.toList()));
        return list;
    }



}
