package com.sf.jkt.k.algorithm.algo.array;

public class CarDpPath {
    public static void main(String[] args) {
        dp1();
        dp2(50);
    }

    public static void  dp2(int inputWeight){
        int[] value=new int[]{60,100,120};
        int[] weight={10,20,40};
        int n=value.length;//物品种类
        int w=inputWeight;//重量格子限制
        int[][]dp=new int[n+1][w+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                if(weight[i-1]<=j){
                    dp[i][j]=Math.max(value[i-1]+dp[i-1][j-weight[i-1]],dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][w]);
//        w=dp[n][w];
        w=inputWeight;
        for(int i=n;i>0;i--){
            if(dp[i][w]!=dp[i-1][w]){
                System.out.println(i+":"+value[i-1]);
                w -= weight[i-1];
            }
        }

    }

    public static void  dp1(){
        int[] price={13,2,3,4,5,8,9,10};
        int p=8;
        int n=price.length;
        int[][]dp=new int[n+1][p+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                if(price[i-1]<=j){
                    dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]],dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][p]);
//        p=dp[n][p];//最大值
        p=8;
        for(int i=n;i>0;i--){
            if(dp[i][p]!=dp[i-1][p]){
                System.out.println(i+":"+price[i-1]);
                p -= price[i-1];
            }
        }
    }

    public static  void  testdppath(){
        int[] price={13,2,3,4,5,8,9,10};
        int p=8;
        int n=price.length;
        int[][] dp=new int[n+1][p+1];//dp二纬表，默认初始化0
        for(int i=1;i<=n;i++){//i 等于第i个物品 对应的价格为 price[i-1];
            for(int j=1;j<=p;j++){//j 代表当前的价格窗口
                if(price[i-1]<=j){// 当前价格窗口能够放下本次物品
                    dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]] ,dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][p]);
        //输出dp
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        //输出路径
        int w=dp[n][p];//w最大值不会超过p,w为最大值的价格窗口
        for (int i=n;i>=1;i--){

           if(dp[i][w]==dp[i-1][w]){//如果最后一行的值等于上一次值，说明本次没有放物品

           }else {
               System.out.println(i+":"+price[i-1]);
               w -= price[i-1];//回到上一次那个价格窗口
           }
        }
//        if(w!=0){
//            System.out.println(1+":"+price[0]);
//        }
    }
    public  static void  testdp(){
        int[] price={1,2,3,4,5,9};
        int p=8;
        int n=price.length;
        int[][] dp=new int[n+1][p+1];//dp二纬表，默认初始化0
        for(int i=1;i<=n;i++){//i 等于第i个物品 对应的价格为 price[i-1];
            for(int j=1;j<=p;j++){//j 代表当前的价格窗口
              if(price[i-1]<=j){// 当前价格窗口能够放下本次物品
                dp[i][j]=Math.max(price[i-1]+dp[i-1][j-price[i-1]] ,dp[i-1][j]);
              }else {
                 dp[i][j]=dp[i-1][j];
              }
            }
        }
        System.out.println(dp[n][p]);
    }
}
