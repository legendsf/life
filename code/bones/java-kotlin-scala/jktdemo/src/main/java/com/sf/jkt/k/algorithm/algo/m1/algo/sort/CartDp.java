package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

public class CartDp {
    int[] price={1,2,3,4,5,9};//每个物品的价格
    int p=8;// 中奖8元
    int n=6; //n种物品
    int[][]dp=new int[n+1][p+1];

    public int maxPrice(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                if(price[i-1]<=j){
                   dp[i][j]=Math.max( price[i-1]+dp[i-1][j-price[i-1]] ,dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        int tp=p;
        for (int i=n;i>0;i--){
            if(dp[i][tp]!=dp[i-1][tp]){
                System.out.println(i+":"+tp+":"+dp[i][tp]);
                tp -= price[i-1];
            }
        }
        return dp[n][p];
    }

    public static void main(String[] args) {
        System.out.println(new CartDp().maxPrice());
    }
}
