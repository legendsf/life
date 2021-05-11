package com.sf.jkt.k.algorithm.algo.array;

import java.util.ArrayList;

public class LoserTree2<T extends Comparable> {
    int size;
    Integer[]tree;//败者树,最小为胜者
    ArrayList<T> leaves;//叶子存放的为值
    int MIN_KEY=-1;//保存不存在的index

    public LoserTree2(ArrayList<T> initValues) {
        this.leaves = initValues;
        size=initValues.size();
        tree=new Integer[size];
        for(int i=0;i<size;i++){
            tree[i]=-1;
        }
        for(int i=size-1;i>=0;i--){
            adjust(i);
        }
    }
    public void adjust(int index){
        int p=(index+size)/2;
        while (p>0){
            if(index>=0&&(tree[p]==-1|| leaves.get(index).compareTo(leaves.get(tree[p]))>0  )){
                int temp=index;//本轮的败者
                index=tree[p];//上一轮的胜者,最小
                tree[p]=temp;//把败者往父亲赋值，
                //最小值继续向父亲传递
            }
            p /= 2;//向上重构
        }
        tree[0]=index;//本次重构后，胜者在的index=0赋值
    }
    public T getLeaf(int index){
        return leaves.get(index);
    }
    public Integer getWinner(){//winner 为null时要删除winner所在的queue，queue为空时，说明都处理完了
        return  tree.length>0?tree[0]:null;
    }
    public void add(int winner,T leaf){
        leaves.set(winner,leaf);
        adjust(winner);
    }
    public void del(int winner){
        leaves.remove(winner);
        tree=new Integer[--size];
        for(int i=0;i<size;i++){
            tree[i]=MIN_KEY;
        }
        for(int i=size-1;i>=0;i--){
            adjust(i);
        }
    }



}
