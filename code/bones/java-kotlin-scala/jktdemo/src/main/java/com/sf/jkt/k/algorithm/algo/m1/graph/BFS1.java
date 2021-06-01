package com.sf.jkt.k.algorithm.algo.m1.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS1 {
    int n, m, dx, dy;
    int[][] data;
    boolean[][] mark;

    public BFS1(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }

    public void bfs(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > m) {
            return;
        }
        if (x == dx && y == dy) {
            System.out.println("true");
            return;
        }
        mark[x][y] = true;
        Queue<Point> queue=new ArrayBlockingQueue<Point>(n*m);
        Point start=new Point(x,y);
        queue.add(start);
        int[][]next={{0,1},{1,0},{0,-1},{-1,0}};
        while (!queue.isEmpty()){
            Point point=queue.poll();
            for (int i=0;i<next.length;i++){
                int nextx=point.x+next[i][0];
                int nexty=point.y+next[i][1];
                if(nextx<1||nextx>n||nexty<1||nexty>m){
                    continue;
                }
                if(data[nextx][nexty]==0 &&!mark[nextx][nexty]){
                    if(nextx==dx&&nexty==dy){
                        System.out.println(true);
                        return;
                    }
                    Point newPoint=new Point(nextx,nexty);
                    mark[nextx][nexty]=true;
                    queue.add(newPoint);
                }

            }
        }
        System.out.println("false");
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
    public static BFS1 create(){
        int[][] data=createGraph();
        boolean[][]mark=new boolean[5+1][4+1];
        return new BFS1(5,4,4,3,data,mark);
    }

    public static void main(String[] args) {
        test1();
    }
}

/*
0 0 0 0 0
0 0 0 1 0
0 0 0 0 0
0 0 0 1 0
0 0 1 0 0
0 0 0 0 1

4 3
1 1
 *
 */