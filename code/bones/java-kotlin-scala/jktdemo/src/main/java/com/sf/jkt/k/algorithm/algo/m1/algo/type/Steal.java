package com.sf.jkt.k.algorithm.algo.m1.algo.type;

public class Steal {
    //递归方法

    public static int cut_rod(int[] p,int n){
        if (n==0){
            return 0;
        }
        int q=-1;
        for (int i=1;i<=n;i++){
            q=Math.max(q,p[i]+cut_rod(p,n-i));
        }
        return q;
    }

    //带备忘的自顶向下
    public static int memoizedCutRod(int[] p,int n){
        int[] r = new int[n+1];
        for (int i = 0;i < r.length;i++){
            r[i] = -1;
        }
        return MEMOIZED_cut_rod_aux(p,n,r);
    }

    public static int MEMOIZED_cut_rod_aux(int[] p,int n,int[]r){
        if(r[n] >= 0){
            return r[n];
        }
        int q = -1;
        if(n == 0) {
            q = 0;
        } else{
            for(int i = 1;i<=n;i++){
                q = Math.max(q,p[i]+MEMOIZED_cut_rod_aux(p,n-i,r));
            }
        }
        r[n] = q;
        return q;
    }

    //使用自底向上
    public static int bottomUpCutRod(int[] p,int n){
        int[] r = new int[n+1];
        for(int i = 0;i<r.length;i++){
            r[i] = 0;
        }
        for(int j = 1;j <= n;j++){
            int q = -1;
            for(int i = 1;i<=j;i++){
                q = Math.max(q, p[i] + r[j-i]);
            }
            r[j] = q;
        }
        return r[n];
    }

    public static void main(String[] args) {
        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        int n = 4;
        int resultDigui = cut_rod(p,n);
        System.out.println("使用递归方法求得最优收益为"+resultDigui);
        int resultBeiwang = memoizedCutRod(p,n);
        System.out.println("使用带备忘方法求得最优收益为"+resultBeiwang);
        int resultZidiXiangshang = bottomUpCutRod(p,n);
        System.out.println("使用自底向上方法求得最优收益为"+resultZidiXiangshang);
    }
}
