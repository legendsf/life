package com.sf.jkt.k.algorithm.algo.m1;

public class BagDp {
    int[] value=new int[]{60,100,120};
    int[] weight={10,20,40};
    int w=50;
    int n=3;
    int[][]dp=new int[n+1][w+1];

    public  int maxValue(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                if(weight[i-1]<=j){
                   dp[i][j]=Math.max( value[i-1]+dp[i-1][j-weight[i-1]] ,dp[i-1][j]);
                }else {
                   dp[i][j]=dp[i-1][j];
                }
            }
        }
        int cw=w;
        for(int i=n;i>0;i--){
           if(dp[i][cw]!=dp[i-1][cw]) {
               System.out.println(i+":"+cw+":"+dp[i][cw]);
               cw -= weight[i-1];
           }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        System.out.println(new BagDp().maxValue());
    }
}
