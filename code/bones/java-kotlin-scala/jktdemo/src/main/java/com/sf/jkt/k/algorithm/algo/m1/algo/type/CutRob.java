package com.sf.jkt.k.algorithm.algo.m1.algo.type;

import java.util.ArrayList;

public class CutRob {
    public static int[] prices = {1,5,8,9,10,17,17,20,24,30};
    private static int[] path; 
    /** 带切割方案的自底向上扩展方案*/
    public static int extended_bottom_up_cut_rod(int n){
        int[] dp = new int[n+1];
        path = new int[n+1];
        dp[0] = 0;
        for(int j = 1; j<=n; j++){
            int max = Integer.MIN_VALUE;
            for(int i=1; i<=j; i++){
                if(max < (prices[i-1] + dp[j-i])){
                    max = prices[i-1] + dp[j-i];
                    path[j] = i;
                }
            }
            dp[j] = max;
        }
        return dp[n];
    }
    /** 得到切割方案(一个最优解)*/
    public static ArrayList<Integer> getACutSolution(int n){
        ArrayList<Integer> result = new ArrayList<>();
        while(n > 0){
            result.add(path[n]);
            n -= path[n];
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("长度为7的最大收益为："+extended_bottom_up_cut_rod(7));
        System.out.println(getACutSolution(7));
    }
}

//输出：
//长度为7的最大收益为：18
//[1, 6]
