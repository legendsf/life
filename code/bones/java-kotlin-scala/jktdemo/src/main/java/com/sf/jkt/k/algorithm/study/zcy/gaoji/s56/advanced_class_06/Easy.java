package com.sf.jkt.k.algorithm.study.zcy.gaoji.s56.advanced_class_06;

public class Easy {
    /**
     * N 1-N个位置
     * M 来到的位置
     * P 可以走的步数
     * K 最终停留的位置
     * 返回值：一共有多少种走法
     * @param N
     * @param M
     * @param P
     * @param K
     * @return
     */
    public static int ways(int N,int M,int P,int K){
        if(N<2|| M <1 ||M>N||P <0||K < 1 ||K>N  ){
            return 0;
        }
        if (P==0){
           return M==K?1:0;
        }
        int res=0;
        if (M==1){
            res=ways(N,M+1,P-1,K);
        }else if(M==N) {
            res=ways(N,M-1,P-1,K);
        }else {
            res=ways(N,M+1,P-1,K)+ways(N,M-1,P-1,K);
        }
        return res;
    }
}
