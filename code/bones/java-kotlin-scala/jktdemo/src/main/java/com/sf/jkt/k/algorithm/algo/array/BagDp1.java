package com.sf.jkt.k.algorithm.algo.array;

public class BagDp1 {
    int[]value;
    int[]weight;
    int n;

    public BagDp1(int[] value, int[] weight) {
        this.value = value;
        this.weight = weight;
        this.n = value.length;//n等于物品种类
    }
    public int getMaxValue(int w){
        int[][]dp=new int[n+1][w+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
               if(weight[i-1]<=j){//能放下
                   dp[i][j]= Math.max(value[i-1]+dp[i-1][j-weight[i-1]] ,dp[i-1][j]);
               }else {
                   dp[i][j]=  dp[i-1][j];
               }
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        int tw=w;
        for(int i=n;i>0;i--){
            if(dp[i][tw]!=dp[i-1][tw]){
                System.out.println(i+":"+value[i-1]);
                tw -= weight[i-1];
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        System.out.println(new BagDp1(new int[]{60, 100, 120}, new int[]{1, 2, 4}).getMaxValue(5));
    }
}
