package com.sf.jkt.k.algorithm.algo.m1.algo.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * https://www.cnblogs.com/allzy/p/5162815.html
 *
 * 比如数组{2,4,-7,5,2,-1,2,-4,3}的最大连续子数组为{5,2,-1,2}，最大连续子数组的和为5+2-1+2=8。问题输入就是一个数组，输出该数组的“连续子数组的最大和”。
 */
public class SmallSum {
    static int[] sarr = {2, 4, -7, 5, 2, -1, 2,-1,1, -4, 3};
//    static int[] sarr = {9, 4, -7, 5, 2, -1, 2,-1,1, -4, 3};
    static int[] subarr = {5, 2, -1, 2};
    static int max = 8;

    public static int smallSum1(int[] arr) {
        int thisSum = 0, maxSum = Integer.MIN_VALUE, i, j, k;
        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr.length; j++) {
                thisSum = 0;
                for (k = i; k <= j; k++) {
                    thisSum += arr[k];
                }
                maxSum = thisSum > maxSum ? thisSum : maxSum;
            }
        }
        return maxSum;
    }

    public static int smallSum2(int[] arr) {
        int thisSum = 0, maxSum = Integer.MIN_VALUE, i, j;
        for (i = 0; i < arr.length; i++) {
            thisSum = 0;
            for (j = i; j < arr.length; j++) {
                thisSum += arr[j];
                maxSum = thisSum > maxSum ? thisSum : maxSum;
            }
        }
        return maxSum;
    }

    public static int smallSum3(int[] arr, int left, int right) {
        int maxLeftSum = 0, maxRightSum = 0, i, center;
        int maxLeftBorderSum = 0, maxRightBorderSum = 0, leftBorderSum = 0, rightBorderSum = 0;
        if (left == right) {
            if (arr[left] > 0) {
                return arr[left];
            }
            return 0;
        }
        center = left + (right - left) / 2;
        for (i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }
        for (i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        maxLeftSum = smallSum3(arr, left, center);
        maxRightSum = smallSum3(arr, center + 1, right);
        return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBorderSum + maxRightBorderSum);
    }

    public static int smallSum4(int[] arr) {
        return smallSum5(arr, 0, arr.length - 1);
    }


    public static int smallSum5(int[] arr, int left, int right) {
        int maxLeftSum = 0, maxRightSum = 0, i, center;
        int maxLeftBorderSum = 0, maxRightBorderSum = 0, leftBorderSum = 0, rightBorderSum = 0;
        if (left == right) {
            if (arr[left] > 0) {
                return arr[left];
            }
            return 0;
        }
        center = left + (right - left) / 2;

        maxLeftSum = smallSum3(arr, left, center);
        maxRightSum = smallSum3(arr, center + 1, right);
        /**
         * 第一，其他部分计算前的值有没有被递归中改变
         * 第二，其他计算后的值递归有没有用到
         * 第三,递归中你可以认为是顺序执行，调用到其他方法
         * 第4，怎么判断归，就是索引是0，1 两个点，看最后执行怎么返回的
         *      验证了退出条件，验证了边界
         * 初始化/保持/终止
         * 第五，一定要有枚举思维，第一层循环从第一个元素，第二层循环从第一个元素
         *  最后每一步怎么做的
         *  第六，遇到问题要冷静，最起码要会枚举
         */
        for (i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }
        for (i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBorderSum + maxRightBorderSum);
    }

    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static int maxSubArray1(int[] nums){
        if(true){
            return maxSubArray(nums);
        }
        if(nums==null||nums.length<1){
            return 0;
        }
        int res=nums[0];
        for(int i=1;i<nums.length;i++){
            nums[i] += Math.max(nums[i-1],0);
            res=Math.max(res,nums[i]);
        }


        return  res;
    }

    /**
     * dp 要搞清楚base 值从哪里开始算
     *  有末尾那行往第一行找base值
     *  有从末尾那一列到第一列找base值
     *  看你循环是 for i for j，还是 for j for i
     *  另外有的dp数组的长/高 都比原始值大1
     *  有的dp数组和原始值一样长和高
     *  dp数组有一维，二维的，N维的
     *  0.搞明白dp 代表什么，要举个例子
     *  1 遍历的顺序，
     *  2.i j之间的关系
     *  3.dp 和原始值之间的关系
     *  4. dp[i] dp[i+1] dp[i-1] 的关系
     *  5. dp[j] 和 dp[j+1] dp[j-1]之间的关系
     *  6.dp 存的值要再加工
     *
      * @param arr
     * @return
     */
    public static int dpMaxSubArray(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int[] dp=new int[arr.length];
        dp[0]=arr[0];
        int res=0;
        for(int i=1;i<arr.length;i++){
            dp[i]=Math.max(arr[i]+dp[i-1],arr[i]);
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    public static int dpMaxSubArray1(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int res=0;
        int[] dp=new int[arr.length];
        dp[0]=arr[0];
        for(int i=1;i<arr.length;i++){
            dp[i]=Math.max(arr[i],arr[i]+dp[i-1]);
            res =Math.max(res,dp[i]);
        }
        return res;
    }

    public static int dpMaxSubArray2(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int res=0;
        int[] dp=new int[arr.length];
        dp[0]=arr[0];
        List<Integer> maxList=new ArrayList<>();
        maxList.add(arr[0]);
        List<Integer> list=new ArrayList<>();
        list.add(arr[0]);
        for(int i=1;i<arr.length;i++){
            boolean needClear=false;
            if(arr[i]<arr[i]+dp[i-1]){
                dp[i]=arr[i]+dp[i-1];
                list.add(arr[i]);
            }else {
                dp[i]=arr[i];
                needClear=true;
                list.clear();;
                list.add(arr[i]);
            }
//            dp[i]=Math.max(arr[i],arr[i]+dp[i-1]);
//            res =Math.max(res,dp[i]);
            if(res<dp[i]){
                res=dp[i];
                maxList.clear();
                maxList.addAll(list);
            }else {
                //list 不做任何处理
            }

        }
        list.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        maxList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return res;
    }

    public static int dpMaxSubArray4(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int[]dp=new int[arr.length];
        dp[0]=arr[0];
        List<Integer> maxList=new ArrayList<>();
        maxList.add(arr[0]);
        List<Integer> dpList=new ArrayList<>();
        dpList.add(arr[0]);
        int ans=0;
        for (int i=1;i<arr.length;i++){
            if(arr[i]<arr[i]+dp[i-1]){
                dp[i]=arr[i]+dp[i-1];
            }else {
                dp[i]=arr[i];
                dpList.clear();
            }
            dpList.add(arr[i]);
            if(ans<dp[i]){
                ans=dp[i];
                maxList.clear();
                maxList.addAll(dpList);
            }
        }
        maxList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return  ans;
    }


    public static int dpMaxSubArray6(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int[]dp=new int[arr.length];
        List<Integer> maxList=new ArrayList<>();
        List<Integer> dpList=new ArrayList<>();
        int ans=0;
        for (int i=0;i<arr.length;i++){
            if(i>1&&arr[i]<arr[i]+dp[i-1]){
                dp[i]=arr[i]+dp[i-1];
            }else {
                dp[i]=arr[i];
                dpList.clear();
            }
            dpList.add(arr[i]);
            if(ans<dp[i]){
                ans=dp[i];
                maxList.clear();
                maxList.addAll(dpList);
            }
        }
        Arrays.stream(dp).forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        maxList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return  ans;
    }


    public static int dpMaxSubArray5(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int res=0;
        int[] dp=new int[arr.length];
        List<Integer> dpList=new ArrayList<>();
        List<Integer> maxList=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(i>1&&arr[i]<arr[i]+dp[i-1]){
                dp[i]=arr[i]+dp[i-1];
            }else {
                dp[i]=arr[i];
                dpList.clear();
            }
            dpList.add(arr[i]);
            if(res<dp[i]){
                res=dp[i];
                maxList.clear();
                maxList.addAll(dpList);
            }
        }
        Arrays.stream(dp).forEachOrdered(x->System.out.print(x+" "));
        maxList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return res;
    }
    public static void test(){
        System.out.println(smallSum1(sarr));
        System.out.println(smallSum4(sarr));
//        System.out.println(smallSum2(sarr));
        System.out.println(smallSum1(new int[]{-1, -2}));
        System.out.println(smallSum4(new int[]{-1, -2}));
        System.out.println(maxSubArray(sarr.clone()));
        System.out.println(maxSubArray(sarr.clone()));
        System.out.println(maxSubArray1(sarr.clone()));
        System.out.println(dpMaxSubArray(sarr.clone()));
        System.out.println(dpMaxSubArray1(sarr.clone()));
        System.out.println(dpMaxSubArray2(sarr.clone()));
        System.out.println(dpMaxSubArray4(sarr.clone()));
    }

    public static void main(String[] args) {

        System.out.println(dpMaxSubArray6(sarr.clone()));
    }

}
