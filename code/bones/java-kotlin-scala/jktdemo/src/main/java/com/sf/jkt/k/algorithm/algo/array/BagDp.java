package com.sf.jkt.k.algorithm.algo.array;

public class BagDp {
    public static void main(String[] args) {
        int[] value=new int[]{60,100,120};
        int[] weight={10,20,40};
        int w=50;
        int n=3;
        int dp[][]=new int[n+1][w+1];
        for(int i=1;i<=n;i++){
            for (int j=1;j<=w;j++){
                if(weight[i-1]<=j){//当前重量小于等于j
                    //（当前的钱+剩余重量的上一个物品能放下的钱）和 当前重量上一个物品的钱对比取大的
                    dp[i][j]=Math.max(value[i-1]+dp[i-1][j-weight[i-1]] ,dp[i-1][j]);
                }else {//装不下
                  dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][w]);
    }
}
