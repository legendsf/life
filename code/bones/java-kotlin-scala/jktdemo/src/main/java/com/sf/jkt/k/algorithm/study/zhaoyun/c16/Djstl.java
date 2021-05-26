package com.sf.jkt.k.algorithm.study.zhaoyun.c16;

import java.util.Scanner;

public class Djstl {

	static   int  M=Integer.MAX_VALUE;
	static   int MI=Integer.MIN_VALUE;
	static   int[]dis={M,M,M,M,M,M,M};
	static {
		dis=new int[]{M,0,M,10,M,30,100};//初始值,1到n的值
	}
	static   int[][]value={
			{0   ,0     ,0    ,0    ,0    ,0     ,0    },
			{0   ,0     ,-1   ,10   ,-1   ,30    ,100    },
			{0   ,-1    ,0    ,5    ,-1   ,-1    ,-1   },
			{0   ,-1    ,-1   ,0    ,50   ,-1    ,-1   },
			{0   ,-1    ,-1   ,-1   ,0    ,-1    ,10   },
			{0   ,-1    ,-1   ,-1   ,20   ,0     ,60   },
			{0   ,-1    ,-1   ,-1   ,-1   ,-1    ,0   }
	};

	/**
	 6
	 8
	 1
	 1 3 10
	 1 5 30
	 1 6 100
	 2 3 5
	 3 4 50
	 4 6 10
	 5 4 20
	 5 6 60


	 *
	 */
	public static void test0(){
		int n, m, x; // n表示点数，m表示边数，x表示我们要的七点
		Scanner cin = new Scanner(System.in);

		n = cin.nextInt();
		m = cin.nextInt();
		x = cin.nextInt();

		int value[][] = new int[n + 1][n + 1]; // 表示点到点的邻接矩阵
		int dis[] = new int[n + 1]; // 存最短路径的
		for (int i = 1; i <= n; i++) {
			dis[i] = Integer.MAX_VALUE;
			for (int j = 1; j <= n; j++) {
				// 初始化我们的地图
				value[i][j] = -1; // 表示没有路的
				if (i == j) {
					value[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < m; i++) { // 表示要输入m个边
			int xx = cin.nextInt();
			int yy = cin.nextInt();
			int v = cin.nextInt(); // xx yy v表示从xx到yy有一条路 长度是v
			value[xx][yy] = v;
			// dis其实在第一个点时候可以在这里计算
			if (xx == x) {
				dis[yy] = v;
			}
		}
		seach(x,dis,value,n);
	}
	public static void test1(){
		seach(1, dis, value, 6);
	}
	public static void main(String[] args) {
		test1();
//		test0();
	}

	public static void seach(int x, int dis[], int value[][], int n) {
		boolean mark[] = new boolean[n + 1];
		mark[x] = true;
		dis[x] = 0;
		int count = 1;
		while (count <= n) {	//O（n^2）
			int loc = 0; // 表示新加的点
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) { // 求dis里面的最小值 优先队列,堆 logn
				if (!mark[i] && dis[i] < min) {
					min = dis[i];
					loc = i;
				}
			}
			if (loc == 0)
				break; // 表示没有可以加的点了
			mark[loc] = true;
			//只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis 最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
			for (int i = 1; i <= n; i++) {
				if (value[loc][i] != -1 && (dis[loc] + value[loc][i] < dis[i])) {
					dis[i] = dis[loc] + value[loc][i];
				}
			}
			count++;
		}
		for (int i = 1; i <= n; i++) {
			System.out.println(x + "到 " + i + "的最短路径为 ：" + dis[i]);
		}

	}

}
