package com.sf.jkt.k.algorithm.algo.m1.algo.type;

/**
 * https://blog.csdn.net/cyp331203/article/details/42806159
 */
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

    public static int cut_road1(int[]p,int n){
        if (n==0){
            return 0;
        }
        int q=-1;
        for (int i=1;i<=n;i++){
            q=Math.max(q,p[i]+cut_road1(p,n-i));
        }
        return q;
    }
    public static int  memoizedCutRod1(int[] p,int n){
        int[] r=new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=-1;
        }
        return MEMOIZED_cut_rod_aux1(p,n,r);
    }
    public static int MEMOIZED_cut_rod_aux1(int[]p,int n,int[]r){
       if (r[n]>=0){
           return r[n];
       }
       int q=-1;
       if (n==0){
          return 0;
       }else {
          for (int i=1;i<=n;i++){
              q=Math.max(q,p[i]+MEMOIZED_cut_rod_aux1(p,n-i,r));
          }
       }
       r[n]=q;
       return q;
    }

    /***
     * https://segmentfault.com/a/1190000039310334
     * @param p
     * @param n
     * @return
     */
    public static int bottomUpCutRoad(int[]p,int n){
        int[]r=new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }
        for (int j=1;j<=n;j++){
            int q=-1;
            for (int i=1;i<=j;i++){
                q=Math.max(q,p[i]+r[j-i]);
            }
            r[j]=q;
        }
        return r[n];
    }

    public static int bottomUpCutRod1(int[] p,int n){
       int[]r=new int[n+1];
       for (int i=0;i<r.length;i++){
           r[i]=0;
       }
       for (int j=1;j<=n;j++){
           int q=-1;
           for (int i=1;i<=j;i++){
               q=Math.max(q,p[i]+r[j-i]);
           }
           r[j]=q;
       }
       return r[n];
    }

    /**
     * https://blog.csdn.net/unsignedccccc/article/details/109333556
     * @param p
     * @param n
     * @return
     */
    public static int bottomUpCutRod2(int[]p,int n){
        int[]dp=new int[n+1];
        for (int j=1;j<=n;j++){
            int q=-1;
            for (int i=1;i<=j;i++){
                q=Math.max(q,p[i]+dp[j-i]);
            }
            dp[j]=q;
        }
        return dp[n];
    }

    public static int bottomUpCutRod3(int[]p,int n){
        if (n>p.length){
            int[]temp=new int[n+1];
            System.arraycopy(p,0,temp,0,p.length);
            for (int i=p.length;i<=n;i++){
                temp[i]=p[p.length-1];//
            }
            p=temp;
        }
        int[]dp=new int[n+1];
        int[]path=new int[n+1];
        for (int j=1;j<=n;j++){
            int q=-1;
            for (int i=1;i<=j;i++){
                if (q<p[i]+dp[j-i]){
                    q=p[i]+dp[j-i];
                    path[j]=i;
                }
            }
            dp[j]=q;
        }
        int tn=n;
        while (tn>0){
            System.out.print(tn+" ");
            tn -= path[n];
        }
        System.out.println();
        return dp[n];
    }

    public static void main(String[] args) {
        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        int n = 4;//长度为4的钢管
        int resultDigui = cut_rod(p,n);
        System.out.println(cut_road1(p,n));
        System.out.println("使用递归方法求得最优收益为"+resultDigui);
        int resultBeiwang = memoizedCutRod(p,n);
        System.out.println(memoizedCutRod1(p,n));
        System.out.println("使用带备忘方法求得最优收益为"+resultBeiwang);
        int resultZidiXiangshang = bottomUpCutRod(p,n);
        System.out.println(bottomUpCutRod(p, n));
        System.out.println(bottomUpCutRod2(p, n));
        System.out.println(bottomUpCutRod3(p, 12));
        System.out.println("使用自底向上方法求得最优收益为"+resultZidiXiangshang);
    }
}
