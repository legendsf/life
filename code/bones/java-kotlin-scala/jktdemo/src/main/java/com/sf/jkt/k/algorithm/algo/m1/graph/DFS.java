package com.sf.jkt.k.algorithm.algo.m1.graph;

/*
0 0 0 0 0
0 0 0 1 0
0 0 0 0 0
0 0 0 1 0
0 0 1 0 0
0 0 0 0 1

dx,dy:4 3
sx,sy:1 1

minstep:7

**
   */
public class DFS {
    public int n, m, dx, dy, minStep = Integer.MAX_VALUE;
    public int[][] data, next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean[][] mark;

    public DFS(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }



    public void dfs(int x, int y) {
        dfs(x, y, 0);
    }

    public void dfs(int x, int y, int step) {
        if (x == dx && y == dy) {
            if (step < minStep) {
                minStep = step;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextx = x + next[i][0];
            int nexty = y + next[i][1];
            if (nextx < 1 || nextx > n || nexty < 1 || nexty > m) {
                continue;
            }
            if (data[nextx][nexty] == 0 && !mark[nextx][nexty]) {
                mark[nextx][nexty] = true;
                dfs(nextx, nexty, step + 1);
                mark[nextx][nexty] = false;
            }
        }
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
        DFS d1, d2, d3, d4;
        (d1 = create()).dfs(1, 1);
        (d2 = create()).dfs(1, 2);
        (d3 = create()).dfs(2, 2);
        (d4 = create()).dfs(1, 3);
        System.out.println(d1.minStep);
        System.out.println(d2.minStep);
        System.out.println(d3.minStep);
        System.out.println(d4.minStep);
    }

    public static DFS create() {
        int[][] data = createGraph();
        boolean[][] mark = new boolean[5 + 1][4 + 1];
        return new DFS(5, 4, 4, 3, data, mark);
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
