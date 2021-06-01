package com.sf.jkt.k.algorithm.algo.m1.algo.arr;

public class SmallSum1 {
    static int[] sarr = {2, 4, -7, 5, 2, -1, 2,-1,1, -4, 3};

    public static int bigSum(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int sum=Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                int thisSum=0;
                for (int k=i;k<=j;k++){
                    thisSum+=arr[k];
                }
                sum=thisSum>sum?thisSum:sum;
            }
        }
        return sum;
    }

    public static int bigSum(int[]arr,int left,int right){
        if(left==right){
            if(arr[left]<0){
                return 0;
            }
            return arr[left];
        }
        int mid=left+(right-left)/2;
        int leftSum=bigSum(arr,left,mid);
        int rightSum=bigSum(arr,mid+1,right);
        int maxBorderLeft=0,maxBorderRight=0,sumBorderLeft=0,sumBorderRight=0;
        for (int i=mid;i>=left;i--){
            sumBorderLeft +=arr[i];
            if(sumBorderLeft>maxBorderLeft){
                maxBorderLeft=sumBorderLeft;
            }
        }
        for (int j=mid+1;j<=right;j++){
            sumBorderRight += arr[j];
            if(sumBorderRight>maxBorderRight){
                maxBorderRight=sumBorderRight;
            }
        }
        return Math.max(Math.max(leftSum,rightSum),maxBorderLeft+maxBorderRight);
    }

    public static int maxSubArray(int[] nums){
        int res=nums[0];
        for (int i=1;i<nums.length;i++){
            nums[i] += Math.max(nums[i-1],0);
            res=Math.max(res,nums[i]);
        }
        return res;
    }
    public static int maxSubArray1(int[] nums){
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        int res=0;
        for (int i=1;i<nums.length;i++){
//            dp[i]=Math.max(nums[i],nums[i]+dp[i-1]);
            dp[i]=nums[i]+Math.max(0,dp[i-1]);
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    public static int maxSubArray2(int[] nums) {
        int max = nums[0];
        int former = 0;//用于记录dp[i-1]的值，对于dp[0]而言，其前面的dp[-1]=0
        int cur = nums[0];//用于记录dp[i]的值
        for(int num:nums){
            cur = num;
            if(former>0) {
                cur +=former;
            }
            if(cur>max){
                max = cur;
            }
            former=cur;
        }
        return max;
    }

    public static int maxSubArray3(int[] nums){
        int max=nums[0];
        int fromer=0;
        int cur=nums[0];
        for (int num:nums){
           cur=num;
           cur += fromer>0?fromer:0;
           max=Math.max(max,cur);
           fromer=cur;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(bigSum(sarr.clone()));
        System.out.println(maxSubArray(sarr.clone()));
        System.out.println(maxSubArray1(sarr.clone()));
        System.out.println(maxSubArray2(sarr.clone()));
        System.out.println(maxSubArray3(sarr.clone()));
        System.out.println(bigSum(sarr.clone(), 0, sarr.length - 1));
    }
}
