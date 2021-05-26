package com.sf.jkt.k.algorithm.algo.m1.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Bfs {
    int n;
    int m;
    int dx;
    int dy;
    int[][] data;
    boolean[][]mark;

    public void bfs(int x,int y){
        if(x<1||x>n||y<1||y>m){
           return;
        }
        if(x==dx&&y==dy){
            System.out.println("true");
            return;
        }
        mark[x][y]=true;
        Queue<Point> queue=new ArrayBlockingQueue<Point>(n*m);
        Point start=new Point();
        start.x=x;
        start.y=y;
        queue.add(start);
        int[][]next={{0,1},{1,0},{0,-1},{-1,0}};
        while (!queue.isEmpty()){
            Point p=queue.poll();
            for(int i=0;i<4;i++){
                int nextx=p.x+next[i][0];
                int nexty=p.y+next[i][1];
                if(nextx<1||nextx>n||nexty<1||nexty>m){
                    continue;
                }
               if(data[nextx][nexty]==0&&!mark[nextx][nexty]){
                   if(nextx==dx&& nexty==dy){
                       System.out.println("true");
                       return;
                   }
               }
               Point p1=new Point(nextx,nexty);
               mark[nextx][nexty]=true;
               queue.add(p1);
            }
        }
        System.out.println("false");
        return;
    }

}
