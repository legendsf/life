package com.sf.jkt.k.algorithm.algo.array;

import java.util.ArrayList;

/**
 * https://blog.csdn.net/whz_zb/article/details/7425152
 * https://blog.csdn.net/liqing0013/article/details/93473266
 * @param <T>
 */
public class MLoserTree<T extends  Comparable> {
    Integer[]tree=null;
    ArrayList<T>leaves=null;
    int size=0;
    int MIN_VALUE=-1;

    public MLoserTree(ArrayList<T> initValues){
        this.leaves=initValues;
        this.size=initValues.size();
        this.tree=new  Integer[size];
        for(int i=0;i<size;i++){
            tree[i]=MIN_VALUE;
        }
        for(int i=size-1;i>=0;i--){
            addjust(i);
        }
    }

    public  void  addjust(int s){ //s代表上一轮的胜者
        int t=(s+size)/2;
        while (t>0){
           if(s>=0 && (tree[t]==-1 || leaves.get(s).compareTo(leaves.get(tree[t]))>0) ){
              int temp=s;
              s=tree[t];
              tree[t]=temp;
           }
           t /=2;
        }
        tree[0]=s;
    }

}
