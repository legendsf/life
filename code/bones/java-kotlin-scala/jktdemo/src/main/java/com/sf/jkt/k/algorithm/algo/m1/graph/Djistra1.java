package com.sf.jkt.k.algorithm.algo.m1.graph;

import java.util.HashMap;
import java.util.Map;

public class Djistra1 {
    static final int MAX = Integer.MAX_VALUE;
    static final int MIN = Integer.MIN_VALUE;
    static int[][] value = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, -1, 10, -1, 30, 100},
            {0, -1, 0, 5, -1, -1, -1},
            {0, -1, -1, 0, 50, -1, -1},
            {0, -1, -1, -1, 0, -1, 10},
            {0, -1, -1, -1, 20, 0, 60},
            {0, -1, -1, -1, -1, -1, 0}
    };

    public static void search(int x, int[][] value) {
        int n = value.length - 1;
        boolean[] mark = new boolean[n + 1];
        mark[x] = true;
        int[] dis = new int[n + 1];
        int count = 1;
        Map<String, String> path = new HashMap<>();
        //初始化dis
        for (int i = 0; i <= n; i++) {
            dis[i] = MAX;
            if (value[x][i] != -1) {
                dis[i] = value[x][i];
            }
        }
        //贪心计算最小路径
        while (count <= n) {
            //找到最小的路径
            int loc = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (!mark[i] && dis[i] < min) {
                    min = dis[i];
                    loc = i;
                }
            }
            if (loc == 0) {
                //新加入一个点路径没有任何变化，和上一次的值一样
                break;//
            }
            mark[loc]=true;
            if(path.get(""+loc)==null){
               path.put(""+loc,x+"->"+loc);
            }
            for (int i=1;i<=n;i++){
                if(value[loc][i]!=-1&&(value[loc][i]+dis[loc]<dis[i])){
                    dis[i]=dis[loc]+value[loc][i];
                    path.put(""+i,path.get(""+loc)+"->"+i);
                }
            }
            count++;
        }
        for (int i=1;i<=n;i++ ){
            System.out.println(x+"到"+i+"最短路径为"+dis[i]+";path="+path.get(""+i));
        }
    }
    public static void search1(int x,int[][]value){
      int n=value.length-1,count=1;
      int[]dis=new int[n+1];
      boolean[]mark=new boolean[n+1];
      mark[x]=true;
      Map<Integer,String> path=new HashMap<>();
      for (int i=1;i<=n;i++){
          dis[i]=MAX;
          if(value[x][i]!=-1){
              dis[i]=value[x][i];
          }
      }
      while (count<=n){
        int loc=0;
        int min=MAX;
        for (int i=1;i<=n;i++){
            if (!mark[i]&& dis[i]<min){
                min=dis[i];
                loc=i;
            }
        }
        if (loc==0){
            break;
        }
        mark[loc]=true;
        if(path.get(loc)==null){
            path.put(loc,x+"->"+loc);
        }
        for (int i=1;i<=n;i++){
            if(value[loc][i]!=-1&&(value[loc][i]+dis[loc]<dis[i])){
                dis[i]=dis[loc]+value[loc][i];
                path.put(i,path.get(loc)+"->"+i);
            }
        }
        count++;
      }
      for (int i=1;i<=n;i++){
         if(dis[i]==MAX){
             System.out.println(x+"到"+i+"的距离为"+"没有路"+";path为"+"没有路");
         }else if(dis[i]==0&&path.get(i)==null){
             System.out.println(x+"到"+i+"的距离为"+0+";path为"+"自己");
         }else {
             System.out.println(x+"到"+i+"的距离为"+dis[i]+";path为"+path.get(i));
         }
      }
    }

    public static void test1(){
        search1(2,value);
    }

    public static void main(String[] args) {
        test1();
    }

}
