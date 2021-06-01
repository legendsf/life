package com.sf.jkt.k.algorithm.algo.m1.graph;

public class DFS1 {
   public int n, m, dx, dy, minStep = Integer.MAX_VALUE;
   public int[][] data, next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
   public boolean[][] mark;

   public DFS1(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
      this.n = n;
      this.m = m;
      this.dx = dx;
      this.dy = dy;
      this.data = data;
      this.mark = mark;
   }

   public void dfs(int x, int y, int step) {
      if (x == dx && y == dy) {
         if (step < minStep) {
            minStep = step;
         }
         return;
      }
      for (int i = 0; i < 4; i++) {
         int nx = next[i][0] + x;
         int ny = next[i][1] + y;
         if (nx < 1 || nx > n || ny < 1 || ny > m) {
            continue;
         }
         if (data[nx][ny] == 0 && !mark[nx][ny]) {
            mark[nx][ny] = true;
            dfs(nx, ny, step + 1);
            mark[nx][ny] = false;
         }
      }
   }

   public void dfs1(int x,int y,int step){
      if(x<1||x>n||y<1||y>m){
         return;
      }
      if(x==dx&&y==dy){
         if(step<minStep){
            minStep=step;
         }
         return;
      }
      for (int i=0;i<next.length;i++){
         int nx=x+next[i][0];
         int ny=y+next[i][1];
         if(nx<1||nx>n||ny<1||ny>m){
            continue;
         }
         if(data[nx][ny]==0&&!mark[nx][ny]){
            mark[nx][ny]=true;
            dfs(nx,ny,step+1);
            mark[nx][ny]=false;
         }
      }
   }

   public void dfs(int x, int y) {
      dfs1(x, y, 0);
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
      DFS1 d1,d2,d3,d4;
      (d1=create()).dfs(1, 1);
      (d2=create()).dfs(1, 2);
      (d3=create()).dfs(2, 2);
      (d4=create()).dfs(1, 3);
      System.out.println(d1.minStep);
      System.out.println(d2.minStep);
      System.out.println(d3.minStep);
      System.out.println(d4.minStep);
   }

   public static DFS1 create() {
      int[][] data = createGraph();
      boolean[][] mark = new boolean[5 + 1][4 + 1];
      return new DFS1(5, 4, 4, 3, data, mark);
   }

   public static void main(String[] args) {
      test1();


   }
}