package com.sf.jkt.k.algorithm.algo.m1.algo.arr;

public class SmallSum2 {
    static int[] sarr = {2, 4, -7, 5, 2, -1, 2, -1, 1, -4, 3};

    public static int maxSubArray1(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                }
                res = Math.max(res, thisSum);
            }
        }
        return res;
    }

    public static int maxSubArray2(int[] arr, int left, int right) {
        if (left == right) {
            if (arr[left] < 0) {
                return 0;
            }
            return arr[left];
        }
        int leftBorderSum = 0, leftBorderMax = 0, rightBorderSum = 0, rightBorderMax = 0;
        int mid = left + (right - left) / 2;
        for (int i = mid; i >= left; i--) {
            leftBorderSum += arr[i];
            leftBorderMax = Math.max(leftBorderMax, leftBorderSum);
        }
        for (int j = mid + 1; j <= right; j++) {
            rightBorderSum += arr[j];
            rightBorderMax = Math.max(rightBorderMax, rightBorderSum);
        }
        int max1 = Math.max(maxSubArray2(arr, left, mid), maxSubArray2(arr, mid + 1, right));
        return Math.max(max1, leftBorderMax + rightBorderMax);
    }

    public static int maxSubArray3(int[] arr) {
        int res = 0;
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i] + Math.max(0, dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int maxSubArray4(int[] arr){
        int res=arr[0];
        for (int i=1;i<arr.length;i++){
            arr[i] += Math.max(0,arr[i-1]);
            res= Math.max(res,arr[i]);
        }
        return res;
    }

    public static int maxSubArray5(int[] arr){
        int max=arr[0];
        int prevDp=0;
        int curDp=arr[0];
        for (int num:arr){
            curDp=num;
            curDp += prevDp>0?prevDp:0;
            max=Math.max(max,curDp);
            prevDp=curDp;
        }
        return max;
    }

    public static void test1(){
        System.out.println(maxSubArray1(new int[]{-3, 4, -2, -1, 3, 2}));
        System.out.println(maxSubArray2(sarr.clone(),0,sarr.length-1));
        System.out.println(maxSubArray3(new int[]{-3, 4, -2, -1, 3, 2}));
        System.out.println(maxSubArray4(new int[]{-3, 4, -2, -1, 3, 2}));
        System.out.println(maxSubArray5(new int[]{-3, 4, -2, -1, 3, 2}));
    }

    public static void main(String[] args) {
       test1();
    }
}
