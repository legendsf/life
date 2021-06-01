package com.sf.jkt.k.algorithm.algo.m1.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS3 {
    int n,m,dx,dy;
    int[][]data;
    boolean[][]mark;

    public BFS3(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }
    public void bfs(int x,int y){
        if(x<1||x>n||y<1||y>m){
            System.out.println("false");
            return;
        }
        if(x==dx&&y==dy){
            System.out.println(true);
            return;
        }
        int[][]next={{0,1},{1,0},{0,-1},{-1,0}};
        Point start=new Point(x,y);
        mark[x][y]=true;
        Queue<Point> queue=new ArrayBlockingQueue<Point>(n*m);
        queue.offer(start);
        int step=0;
        while (!queue.isEmpty()){
          Point point=queue.poll();
          for (int i=0;i<next.length;i++){
              int nx=point.x+next[i][0];
              int ny=point.y+next[i][1];
              if(nx<1||nx>n||ny<1||ny>m){
                  continue;
              }
              if(data[nx][ny]==0&&!mark[nx][ny]){
                  if(nx==dx&&ny==dy){
                      System.out.println("true");
                      return;
                  }
                  Point point1=new Point(nx,ny);
                  mark[nx][ny]=true;
                  queue.offer(point1);
              }
          }
        }
        System.out.println("false");
    }


    public static int[][] createGraph() {
        int[][] data = new int[6][5];
        data[1][3] = 1;
        data[3][3] = 1;
        data[4][2] = 1;
        data[5][4] = 1;
        return data;
    }

    public static void test1() {
        create().bfs(1, 1);
        create().bfs(1, 2);
        create().bfs(2, 2);
        create().bfs(1, 3);
    }

    public static BFS3 create() {
        int[][] data = createGraph();
        boolean[][] mark = new boolean[5 + 1][4 + 1];
        return new BFS3(5, 4, 4, 3, data, mark);
    }

    public static void main(String[] args) {
        test1();

    }
}
