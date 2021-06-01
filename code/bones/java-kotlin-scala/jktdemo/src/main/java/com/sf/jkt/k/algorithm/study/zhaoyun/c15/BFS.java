package com.sf.jkt.k.algorithm.study.zhaoyun.c15;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

class Point {

	int x;
	int y;
}

/*
0 0 0 0 0
0 0 0 1 0
0 0 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 1

从1，1 开始找
dx,dy: 4 3
start: 1 1
 *
 */

public class BFS {

	int n; // 地图的行
	int m; // 地图的列
	int dx; // 安琪的位置x
	int dy; // 安琪的位置y
	int data[][]; // 邻接矩阵
	boolean mark[][]; // 标记数据 走过的位置

	public BFS(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
		this.n = n;
		this.m = m;
		this.dx = dx;
		this.dy = dy;
		this.data = data;
		this.mark = mark;
	}

	public void bfs(int x, int y) { // x he y表示你当前的位置,就是求（x，y）->(dx,dy)能不能到
		if(x < 1 || x > n || y < 1 || y > m) {
			return ;
		}
		if(x == dx && y == dy) {
			System.out.println("true");
			return ;
		}
		mark[x][y] = true;

		Queue<Point> queue = new ArrayBlockingQueue<Point>(n * m); // 因为最多也就是n*m个点
		Point start = new Point();
		start.x = x;
		start.y = y;
		queue.add(start);
		int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };	//ACM想到的

		while (!queue.isEmpty()) {		//O(n)
			Point point = queue.poll(); // 拿出队列的第一个点
			for(int i = 0 ; i < 4; i++) {
				int nextx = point.x + next[i][0];
				int nexty = point.y + next[i][1];
				if(nextx < 1 || nextx > n || nexty < 1 || nexty > m) {//边界从1开始
					continue;
				}
				if(data[nextx][nexty] == 0 && !mark[nextx][nexty]) {		//表示可以走
					if(nextx == dx && nexty == dy) {		//表示可以到了 就不走了
						System.out.println("true");
						return ;
					}
					Point newPoint = new Point();
					newPoint.x = nextx;
					newPoint.y = nexty;
					mark[nextx][nexty] = true;
					queue.add(newPoint);
				}
			}
			
		}
		System.out.println("false");
		return ;

	}

	public static int[][] createGraph(){
		int[][]data=new int[6][5];
		data[1][3]=1;
		data[3][3]=1;
		data[4][2]=1;
		data[5][4]=1;
		return data;
	}

	public static void test1(){
		create().bfs(1,1);
		create().bfs(1,2);
		create().bfs(2,2);
	}
	public static BFS create(){
		int[][] data=createGraph();
		boolean[][]mark=new boolean[5+1][4+1];
		return new BFS(5,4,4,3,data,mark);
	}


	public static void main(String[] args) {
		test1();
	}

}
