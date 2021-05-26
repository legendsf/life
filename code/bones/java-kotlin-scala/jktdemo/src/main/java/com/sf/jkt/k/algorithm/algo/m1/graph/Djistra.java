package com.sf.jkt.k.algorithm.algo.m1.graph;

import java.util.HashMap;
import java.util.Map;

public  class  Djistra  {
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

        public static void  search(int x,int[] dis,int[][]value,int n){
                boolean[]mark=new boolean[n+1];
                mark[x]=true;
                dis[x]=0;
                int count=1;
                while (count<=n){
                        int min=Integer.MAX_VALUE;
                        int loc=0;
                        for(int i=1;i<=n;i++){
                                if(!mark[i]&&dis[i]<min){
                                        min=dis[i];
                                        loc=i;
                                }
                        }
                        if(loc==0){
                            System.out.println("没有要加入的点");
                            break;
                        }
                        mark[loc]=true;
                        for(int i=1;i<=n;i++){
                            if(value[loc][i]!=-1&&(dis[loc]+value[loc][i]<dis[i])){
                                dis[i]=dis[loc]+value[loc][i];
                            }
                        }
                        count++;
                }
                for (int i=1;i<=n;i++){
                    System.out.println(x+"到"+i+"的最短路径为："+dis[i]+"; ");
                }
        }

    public static void search1(int x, int dis[], int value[][], int n) {
        boolean mark[] = new boolean[n + 1];
        mark[x] = true;
        dis[x] = 0;
        int count = 1;
        Map<String,String> map=new HashMap<>();
        while (count <= n) {    //O（n^2）
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
            if(map.get(""+loc)==null){
                map.put(""+loc,x+"->"+loc);
            }
            //只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis 最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
            for (int i = 1; i <= n; i++) {
                if (value[loc][i] != -1 && (dis[loc] + value[loc][i] < dis[i])) {
                    dis[i] = dis[loc] + value[loc][i];
                    map.put(""+i,map.get(""+loc)+"->"+i);
                }
            }
            count++;
        }
        for (int i = 1; i <= n; i++) {
//            if(dis[i]==0&&map.get(""+i)==null){
//                System.out.println(x+"到i");
//            }
            System.out.println(x + "到 " + i + "的最短路径为 ：" + dis[i]);
        }

    }

        public static void test1(){
            search1(1,dis,value,6);
        }

    public static void main(String[] args) {
      test1();
    }
}
