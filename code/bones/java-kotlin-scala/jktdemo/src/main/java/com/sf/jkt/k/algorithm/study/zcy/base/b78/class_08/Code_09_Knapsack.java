package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_08;

public class Code_09_Knapsack {

	public static int maxValue1(int[] c, int[] p, int bag) {
		return process1(c, p, 0, 0, bag);
	}

	public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
		if (alreadyweight > bag) {
			return 0;
		}
		if (i == weights.length) {
			return 0;
		}
		return Math.max(
				
				process1(weights, values, i + 1, alreadyweight, bag),
				
				values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));
	}

	public static int maxValue2(int[] c, int[] p, int bag) {
		int[][] dp = new int[c.length + 1][bag + 1];
		for (int i = c.length - 1; i >= 0; i--) {
			for (int j = bag; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + c[i] <= bag) {
					dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
				}
			}
		}
		return dp[0][0];
	}

	public static int maxValue3(int weights[],int[] values,int bag){
		int[][]dp=new int[values.length+1][bag+1];
		for (int i=1;i<dp.length;i++){
			for (int j=1;j<dp[0].length;j++){
				if (weights[i-1]<=j){
					dp[i][j]=Math.max(dp[i-1][j],values[i-1]+dp[i-1][j-weights[i-1]]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		int tw=bag;
		for (int i=dp.length-1;i>0;i--){
			if (dp[i][tw]!=dp[i-1][tw]){
				System.out.println(i+" "+tw+" "+dp[i-1][tw]);
				tw -= weights[i-1];
			}
		}
		return dp[values.length][bag];
	}

	public static void main(String[] args) {
		int[] c = { 3, 2, 4, 7 };//weights
		int[] p = { 5, 6, 3, 19 };//values
		int bag = 11;
		System.out.println(maxValue1(c, p, bag));
		System.out.println(maxValue2(c, p, bag));
		System.out.println(maxValue3(c, p, bag));
	}

}
