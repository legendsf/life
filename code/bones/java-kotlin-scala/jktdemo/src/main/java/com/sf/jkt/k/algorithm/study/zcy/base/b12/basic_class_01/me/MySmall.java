package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.Arrays;

public class MySmall {
    //小和
    public static int smallsum(int[] arr){
        if(arr==null||arr.length<2){return 0;}
        return smallsum(arr,0,arr.length-1);
    }
    public static int smallsum(int[] arr,int l,int r){
        if(l==r){return 0;}
        int mid=l+((r-l)>>1);
        return smallsum(arr,l,mid)+smallsum(arr,mid+1,r)+merge(arr,l,mid,r);
    }
    public static int merge(int[] arr,int l,int mid,int r){
        int[] help = new int[r-l+1];
        int res=0;
        int p1=l;
        int p2=mid+1;
        int index=0;
        while (p1<=mid&&p2<=r){
            res += arr[p1]<arr[p2]? (r-p2+1)*arr[p1]:0;
            help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[index++]=arr[p1++];
        }
        while (p2<=r) {
            help[index++] = arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[l+j]=help[j];
        }
        return res;
    }

    public static final int smallsum2(int[] arr){
       if(arr==null||arr.length<2){return 0;}
       return smallsum2(arr,0,arr.length-1);
    }
    public static final int smallsum2(int[] arr,int l,int r){
       if(l==r){return 0;}
       int mid = l+((r-l)>>1);
       return smallsum2(arr,l,mid)+smallsum2(arr,mid+1,r)+merge2(arr,l,mid,r);
    }
    public static  int merge2(int[] arr,int l,int m,int r){
        int[] help = new int[r-l+1];
        int i=0;
        int res=0;
        int p1=l;
        int p2=m+1;
        while (p1<=m&&p2<=r){
            res += arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=m){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(int j=0;j<help.length;j++){
           arr[l+j]=help[j];
        }
        return res;
    }

    //逆序对
    public static int inversePair(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
       return inversePair(arr,0,arr.length-1);
    }
    public static int inversePair(int[] arr,int l,int r){
       if(l==r){
           return 0;
       }
       int mid= l+((r-l)>>1);
       return inversePair(arr,l,mid)+inversePair(arr,mid+1,r)+mergePair(arr,l,mid,r);
    }
    public static int mergePair(int[] arr,int l,int mid,int r){
        int[] copy = new int[arr.length];
        System.arraycopy(arr,l,copy,l,r-l+1);
        int count=0;
        int index=r;
        int p1=mid;
        int p2=r;
        while (p1>=l&&p2>=mid+1){
            count += arr[p1]>arr[p2]? p2-mid:0;
            copy[index--]=arr[p1]>arr[p2]?arr[p1--]:arr[p2--];
        }
        while (p1>=l){
            copy[index--]=arr[p1--];
        }
        while (p2>=mid+1){
            copy[index--]=arr[p2--];
        }
        for(int i=l;i<=r;i++) {
            arr[i]=copy[i];
        }
        return count;
    }
    //分区，小于等于在左边，大于在右边
    public static void partition(int[] arr,int num){
        if(arr==null||arr.length<2){return;}
        int less =-1;
        int more=arr.length-1;
        int l=0;
        while (l<more){
            if(arr[l]<=num){
               swap(arr,++less,l++);
            }else {
               swap(arr,more--,l);
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        if(i==j){return;}
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];

    }

    public static void netherLandsFlag(int[] arr,int num){
        if(arr==null||arr.length<2){return;}
        int less=-1;
        int more=arr.length-1;
        int i=0;//等于区域
        while (i<more){
           if(arr[i]<num){
               swap(arr,++less,i++);
           }else if(arr[i]>num){
               swap(arr,more--,i);
           }else {
               i++;
           }
        }
    }

    public static int maxGap1(int[] nums){
        if(nums==null||nums.length<2){return 0;}
        int max= Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        int len=nums.length;
        for(int i=0;i<len;i++){
            max=Math.max(max,nums[i]);
            min=Math.min(min,nums[i]);
        }
        if(max==min){
            return 0;
        }
        boolean[] hasNum = new boolean[len+1];
        int[] mins=new int[len+1];
        int[] maxs=new int[len+1];
        int bid=0;

        for (int i=0;i<len;i++){
            bid= bucket(nums[i],len,min,max);
            mins[bid]=hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
            maxs[bid]=hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
            hasNum[bid]=true;
        }
        int lastMax=maxs[0];
        int res=0;
        int i=1;
        for(;i<=len;i++){
            if(hasNum[i]){
               res = Math.max(res,mins[i]-lastMax);
               lastMax=maxs[i];
            }
        }
        return res;
    }

    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

    public static int maxGap2(int[] nums){
        if(nums==null||nums.length<2){return 0;}
        int min=nums[0];
        int max=nums[0];
        int len=nums.length;
        for (int i=0;i<len;i++){
            min=Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }
        if(min==max){
            return 0;
        }
        boolean[] hasNum = new boolean[len+1];
        int[] mins = new int[len+1];
        int[] maxs = new int[len+1];
        int bid=0;

        for(int i=0;i<len;i++){
           bid=bucket(nums[i],len,min,max);
           mins[bid]=hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
           maxs[bid]=hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
             hasNum[bid]=true;
        }
        int res=0;
        int lastMax=maxs[0];

        for(int i=1;i<=len;i++){//<=len
           if(hasNum[i]){
               res = Math.max(res,mins[i]-lastMax);
               lastMax=maxs[i];
           }
        }
        return  res;
    }
    public static int bucket1(long num,long len,long min,long max){
        return (int) ((num-min)*len / (max-min));
    }

    public static int maxGap3(int[] nums){
        if(nums==null||nums.length<2){
            return 0;
        }
        int max=nums[0];
        int min=nums[0];
        int len=nums.length;
        for (int i=0;i<len;i++){
            min=Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }
        if(min==max){
            return 0;
        }

        boolean[] hasNum = new boolean[len+1];
        int[] mins= new int[len+1];
        int[] maxs = new int[len+1];
        int bid=0;
//
        for(int i=0;i<len;i++){
            bid=bucket3(nums[i],len,min,max);
            mins[bid]= hasNum[bid]? Math.min(mins[bid],nums[i]):nums[i];
            maxs[bid]= hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
            hasNum[bid]=true;

        }
//
//        int res=0;
//        int lastMax=maxs[0];
//        for(int i=1;i<=len;i++){
//            if(hasNum[i]){
//                res = Math.max(res,mins[i]-lastMax);
//                lastMax=maxs[i];
//            }
//        }


//        for (int i=0;i<len;i++){
//            bid= bucket(nums[i],len,min,max);
//            mins[bid]=hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
//            maxs[bid]=hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
//            hasNum[bid]=true;
//        }

        int lastMax=maxs[0];
        int res=0;
        int i=1;
        for(;i<=len;i++){
            if(hasNum[i]){
                res = Math.max(res,mins[i]-lastMax);
                lastMax=maxs[i];
            }
        }


        return res;
    }

    public static int bucket3(long num,long len,long min,long max){
        return (int)((num-min)*len/(max-min));
    }

    public static int maxGap4(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
        int len= arr.length;
        int min=arr[0];
        int max=arr[0];
        for(int i=0;i<len;i++){
            min=Math.min(min,arr[i]);
            max=Math.max(max,arr[i]);
        }
        if(min==max){
            return 0;
        }

        boolean[] hasNum = new boolean[len+1];
        int[] mins = new int[len+1];
        int[] maxs= new int[len+1];
        int bid=0;
        for(int i=0;i<arr.length;i++){
            bid= bucket4(arr[i],len,min,max);
            mins[bid]= hasNum[bid]?Math.min(mins[bid],arr[i]):arr[i];
            maxs[bid]= hasNum[bid]?Math.max(maxs[bid],arr[i]):arr[i];
            hasNum[bid]=true;
        }
        int res=0;
        int lastMax=maxs[0];
        for(int i=1;i<=len;i++){
           if(hasNum[i]){
               res =Math.max(res,mins[i]-lastMax);
               lastMax=maxs[i];
           }
        }

        return res  ;
    }

    public static int bucket4(long num,long len,long min,long max){
        return (int)((num-min)*len/(max-min));
    }

    public static void main(String[] args) {
//        Constants.checkSmall(Code_12_SmallSum::smallSum);
//        Constants.checkSmall(MySmall::smallsum2);
//        Constants.checkInverse(MySmall::inversePair);
        int[] arr=new int[]{9,3,5,2,8,10,7,5,6,5};
        int[] arr2=new int[]{9,3,5,2,8,10,7,5,6,5};
        partition(arr,5);
        System.out.println(Arrays.toString(arr));
        netherLandsFlag(arr2,5);
        System.out.println(Arrays.toString(arr2));
        System.out.println(maxGap(arr));
        System.out.println(maxGap1(arr));

    }
}
