package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;
import com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.Code_08_NetherlandsFlag;

import java.util.Arrays;

import static com.sf.jkt.k.algorithm.study.zcy.Constants.*;
public class MNetherLandsFlag {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(10,10);
        int[] partion0= Code_08_NetherlandsFlag.partition(arr,0,arr.length-1,2);
        int[] partion=partion(arr,0,arr.length-1,2);
        if(isEqual(partion,partion0)){
            System.out.println("Nice!");
        }
        Arrays.stream(partion).forEach(System.out::println);
    }

    public static int[] partion(int[]arr,int l,int r,int p){
        int less=l-1;
        int more=r+1;
        while (l<more){
            if(arr[l]<p){
               //小于区扩一位, 交换当前和小于区域扩充后的位置， 当前指针++
                swap(arr,++less,l++);
            }else if(arr[l]>p) {
                //大于区域扩充一位，交换当前位置和扩充后的位置
               swap(arr,--more,l);
            }else {
                l++;
            }
        }
        return new int[]{less+1,more-1};
    }
}
