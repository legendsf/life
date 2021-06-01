package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

import com.sf.jkt.k.algorithm.algo.array.LoserTree2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LooserTree2<T extends Comparable> {
    Integer[] tree;
    ArrayList<T> leaves;
    int size;
    int MIN_KEY=-1;

    public LooserTree2(ArrayList<T> leaves) {
        this.leaves = leaves;
        this.size=leaves.size();
        tree=new Integer[size];
        for (int i=size-1;i>=0;i--){
            tree[i]=MIN_KEY;
        }
        for (int i=size-1;i>=0;i--){
            adjust(i);
        }
    }
    public void adjust(int winner){
        int p=(winner+size)/2;
        while (p>0){
            if (winner>=0&&(tree[p]==-1||leaves.get(winner).compareTo( leaves.get(tree[p]))>0)){
               int temp=winner;
               winner=tree[p];
               tree[p]=temp;
            }
            p/=2;
        }
        tree[0]=winner;
    }

    public void add(int index,T leaf){
        leaves.set(index,leaf);
        adjust(index);
    }

    public void del(int index){
        leaves.remove(index);
        tree=new Integer[--size];
        for (int i=size-1;i>=0;i--){
            tree[i]=MIN_KEY;
        }
        for (int i=size-1;i>=0;i--){
            adjust(i);
        }
    }

    public T getLeaf(int index){
        return leaves.get(index);
    }

    public Integer getWinner(){
        return (tree==null||tree.length<1)?null:tree[0];
    }
    public T getWinnerLeaf(){
       Integer winner=getWinner();
       if (winner==null){
           return null;
       }
       return getLeaf(winner);
    }

    public static void test1(){
        Queue<Integer> queue0=new LinkedList<>();
        Queue<Integer> queue1=new LinkedList<>();
        Queue<Integer> queue2=new LinkedList<>();
        Queue<Integer> queue3=new LinkedList<>();
        Integer[] source0 = {2, 8, 16, 23, 26};
        Integer[] source1 = {4, 13, 22, 23, 29};
        Integer[] source2 = {5, 12, 15, 23, 32};
        Integer[] source3 = {3, 7, 17, 23, 28};
        queue0.addAll(Arrays.asList(source0));
        queue1.addAll(Arrays.asList(source1));
        queue2.addAll(Arrays.asList(source2));
        queue3.addAll(Arrays.asList(source3));
        Queue<Integer>[]sources=new Queue[4];
        sources[0]=queue0;
        sources[1]=queue1;
        sources[2]=queue2;
        sources[3]=queue3;

        ArrayList<Integer> initValues=new ArrayList<>(sources.length);
        for (int i=0;i<sources.length;i++){
            initValues.add(sources[i].poll());
        }
        LoserTree2<Integer> loserTree=new LoserTree2<>(initValues);
        Integer winner=loserTree.getWinner();
        System.out.print(loserTree.getLeaf(winner)+" ");
        while (sources.length>0){
            Integer newLeaf=sources[winner].poll();
            if (newLeaf==null){
                loserTree.del(winner);
            }else {
                loserTree.add(winner,newLeaf);
            }
            winner=loserTree.getWinner();
            if (winner==null){
                break;
            }
            System.out.print(loserTree.getLeaf(winner)+" ");
        }

    }

    public static void main(String[] args) {
        test1();
    }

}
