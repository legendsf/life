package com.sf.jkt.k.algorithm.algo.array;

public class CarDp {
    //求怎么选择的物品最大值能达到多少
    public static void testdp1(){
        int[] price={1,2,3,4,5,9};//每个物品的价格
        int p=8;// 中奖8元
        int n=6; //n种物品
        int dp[][]=new int[n+1][p+1];
        for(int i=1;i<=n;i++){// 没个物品
            for(int j=1;j<=p;j++){//每个价格
                if(price[i-1]<=j){//当前物品价格小于等于当钱可放入价格
                    //dp[i-1][j] 当前价格窗口下上一次最大值
                    //price[i-1] 当前物品的价值
                    // dp[i-1][j-price[i-1]]//剩余的价值窗口上一次的最大值，因为每一次都是当前的最优解
                    dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]] ,dp[i-1][j]);
                }else {//价格买不了当前物品
                    dp[i][j]=dp[i-1][j];//当前金钱下，上一次物品的价值总和就是本次的最大值
                }
            }
        }
        System.out.println(dp[n][p]);
    }
    public static void testdp2(){
        int[] price={1,2,3,4,5,9};//每个物品的价格
        int p=8;// 中奖8元
        int n=6; //n种物品
        int[][]dp=new int[n+1][p+1];
        for(int i=1;i<=n;i++){
            for (int j=1;j<=p;j++){
                if(price[i-1]<=j){
                    dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]],dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][p]);
        for (int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                System.out.print(""+dp[i][j]+" ");
            }
            System.out.println();
        }
        int tp=p;
        for(int i=n;i>0;i--){
            if(dp[i][tp]!=dp[i-1][tp]){
                System.out.println(i+":"+price[i-1]+":"+dp[i][tp]);
                tp -= price[i-1];
            }
        }
    }
    public static void main(String[] args) {
       testdp2();
    }
}
