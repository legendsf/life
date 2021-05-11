package com.sf.jkt.k.algorithm.algo.array;

public class Dp2 {
    public static void cardp(){
        int[] price={1,2,3,4,5,9};//每个物品的价格
        int p=8;// 中奖8元
        int n=6; //n种物品
        int[][]dp=new int[n+1][p+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                if(price[i-1]<=j){
                    dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]],dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        int tp=p;
        System.out.println(dp[n][p]);
        for(int i=n;i>0;i--){
            if(dp[i][tp]!=dp[i-1][tp]){
                System.out.println(i+":"+price[i-1]+":"+dp[i][tp]);
                tp -= price[i-1];
            }
        }
    }

    public static void bagdp(){
        int[] value=new int[]{60,100,120};
        int[] weight={10,20,40};
        int w=50;
        int n=3;
        int[][]dp=new int[n+1][w+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                if(weight[i-1]<=j){
                    dp[i][j]=Math.max( value[i-1]+dp[i-1][j-weight[i-1]] ,dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][w]);
        int cw=w;
        for(int i=n;i>0;i--){
            if(dp[i][cw]!=dp[i-1][cw]){
                System.out.println(i+":weight:"+weight[i-1]+":value:"+value[i-1]+":dp:"+dp[i][cw]);
                cw -= weight[i-1];
            }
        }
    }
    public static void main(String[] args) {
       cardp();
       bagdp();
    }
}
