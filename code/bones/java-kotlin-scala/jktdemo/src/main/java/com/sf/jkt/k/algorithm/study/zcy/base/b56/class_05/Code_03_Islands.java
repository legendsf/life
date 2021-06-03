package com.sf.jkt.k.algorithm.study.zcy.base.b56.class_05;

public class Code_03_Islands {

	public static int countIslands(int[][] m) {
		if (m == null || m[0] == null) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 1) {
					res++;
					infect(m, i, j, N, M);
				}
			}
		}
		return res;
	}

	public static void infect(int[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
			return;
		}
		m[i][j] = 2;
		infect(m, i + 1, j, N, M);
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i, j - 1, N, M);
	}

	public static int countIslands1(int[][]m){
		if (m==null||m.length<1){
			return 0;
		}
		int N=m.length;
		int M=m[0].length;
		int res=0;
		for (int i=0;i<N;i++){
			for (int j=0;j<M;j++){
				if (m[i][j]==1){
					res++;
					infect(m,i,j,N,M);
				}
			}
		}
		return res;
	}
	public static void infect1(int[][]m,int i,int j,int N,int M){
		if (i<0||i>=N||j<0||j>=M||m[i][j]!=1){
			return;
		}
		m[i][j]=2;
		infect(m,i+1,j,N,M);
		infect(m,i-1,j,N,M);
		infect(m,i,j+1,N,M);
		infect(m,i,j-1,N,M);
	}
	public static int[][] createIslands(){
		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 1, 1, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		return m2;
	}

	public static void test1(){
		int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 0, 1, 1, 1, 0 },
				{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 1, 1, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2.clone()));

		System.out.println(countIslands1(createIslands()));
	}

	public static void main(String[] args) {
		test1();

	}

}
