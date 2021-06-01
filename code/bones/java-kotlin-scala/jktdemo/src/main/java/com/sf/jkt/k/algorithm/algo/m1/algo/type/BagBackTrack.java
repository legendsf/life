package com.sf.jkt.k.algorithm.algo.m1.algo.type;

/**
 * https://blog.csdn.net/Double2hao/article/details/51733136
 */
public class BagBackTrack {
    int c = 30;    //背包容量
    int n = 3;    //对象数目
    int w[] = {20, 15, 15};  //对象重量数组
    int v[] = {40, 25, 25};  //对象收益数组
    int cw;   //当前背包重量
    int cv;   //当前背包价值
    int bestv;//迄今最大的收益
    int[] X = new int[n];  //记录在树中的移动路径,为1的时候表示选择该组数据，为0的表示不选择该组数据

    void getBest(int i) {
        if (i >= n)//递归结束的判定条件
        {
            if (cv > bestv) {
                bestv = cv;
            }

            return;
        }
        if (cw + w[i] <= c)//进入该节点的右孩子（值为1的孩子）
        {
            X[i] = 1;
            cw += w[i];
            cv += v[i];
            getBest(i + 1);
            cw -= w[i];//此处回溯
            cv -= v[i];
        }

        X[i] = 0;//进入该节点的右孩子（值为0的孩子）
        getBest(i + 1);
    }



    public  void  getBest1(int i){
        if(i>=n){
            bestv=Math.max(bestv,cv);
            return;
        }
        if(cw+w[i]<=c){
            X[i]=1;
            cw += w[i];
            cv += v[i];
            getBest(i+1);
            cw -=w[i];//此处回溯
            cv -= v[i];
        }
        X[i]=0;
        getBest(i+1);
    }

    public static int getMaxDivide(Integer[] arr,int start ,int end){
        if(start==end){//终止
            return arr[start];
        }else {
            int mid= start+ (end-start)/2;
            int left=getMaxDivide(arr,start,mid);//递
            int right=getMaxDivide(arr,mid+1,end);//di
            return left>right?left:right;//gui
        }

    }

    public void getBest2(int i){
        if (i>=n){
            bestv=Math.max(cv,bestv);
            return;
        }
        if (cw+w[i]<=c){
            X[i]=1;
            cw +=w[i];
            cv+=v[i];
            getBest2(i+1);
            cw -=w[i];
            cv -=v[i];
        }
        X[i]=0;
        getBest2(i+1);
    }

    public void getBest3(int i){
        if (i>=n){
            bestv=Math.max(bestv,cv);
            return;
        }
        if (cw+w[i]<=c){
            X[i]=1;
            cw +=w[i];
            cv +=v[i];
            getBest3(i+1);
            cw -=w[i];
            cv -= v[i];
        }
        X[i]=0;
        getBest3(i+1);

    }

    public void getBest4(int i){
       if(i>=n){
           bestv=Math.max(bestv,cv);
           return;
       }
       if(cw+w[i]<=c){
          cw += w[i];
          cv += v[i];
          getBest4(i+1);
          cw -= w[i];
          cv -= v[i];
       }
       getBest4(i+1);
    }

    public  void getBest5(){
        int[][]dp=new int[n+1][c+1];
        for (int i=1;i<=n;i++){
            for (int j=1;j<=c;j++){
                if (w[i-1]<=j){
                   dp[i][j]=Math.max(dp[i-1][j],v[i-1]+dp[i-1][j-w[i-1]]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][c]);
    }

    public static void test1() {
        BagBackTrack bt = new BagBackTrack();
        bt.getBest(0);
        System.out.println(bt.bestv);
        bt=new BagBackTrack();
        bt.getBest1(0);
        System.out.println(bt.bestv);
        BagBackTrack bt2 = new BagBackTrack();
        bt2.getBest2(0);
        System.out.println(bt2.bestv);
        BagBackTrack bt3 = new BagBackTrack();
        bt3.getBest4(0);
        System.out.println(bt3.bestv);
        System.out.println("---");
        bt3.getBest5();
    }

    public static void main(String[] args) {
        test1();
    }

}
