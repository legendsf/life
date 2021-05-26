package com.sf.jkt.k.algorithm.algo.m1.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LoserTree<T extends Comparable> {
   Integer[] tree;
   ArrayList<T> leaves;
   int MIN_KEY=-1;
   int size;

   public LoserTree(ArrayList<T> leaves) {
      this.leaves = leaves;
      size=leaves.size();
      tree=new Integer[size];
      for(int i=0;i<size;i++){
         tree[i]=MIN_KEY;
      }
      for (int i=size-1;i>=0;i--){
         adjust(i);
      }
   }

   public void adjust(int i){
      int p=(size+i)/2;
      while (p>0){
         if(i>=0&&(tree[p]==-1||leaves.get(i).compareTo(leaves.get(tree[p]))>0)){
            int temp=i;//临时值
            i=tree[p];//保留上一轮的胜者,小的胜大的败
            tree[p]=temp;//父亲保留最大的败者
         }
         p /= 2;
      }
      tree[0]=i;
   }

   public void add(int i,T value){
      leaves.set(i,value);
      adjust(i);
   }
   public void  del(int i){
      leaves.remove(i);
      tree=new Integer[--size];
      for(int j=size-1;j>=0;j--){
         tree[j]=MIN_KEY;
      }
      for(int j=size-1;j>=0;j--){
         adjust(j);
      }
   }
   public T getLeaf(int i){
      return leaves==null?null:leaves.get(i);
   }

   public Integer getWinner(){
      return (tree==null||tree.length<1)?null:tree[0];
   }

   public static void test1() {
      //假设当前有 4 个归并段
      Queue<Integer> queue0 = new LinkedList();
      Queue<Integer> queue1 = new LinkedList();
      Queue<Integer> queue2 = new LinkedList();
      Queue<Integer> queue3 = new LinkedList();
      Integer[] source0 = {2, 8, 16, 23, 26};
      Integer[] source1 = {4, 13, 22, 23, 29};
      Integer[] source2 = {5, 12, 15, 23, 32};
      Integer[] source3 = {3, 7, 17, 23, 28};
      queue0.addAll(Arrays.asList(source0));
      queue1.addAll(Arrays.asList(source1));
      queue2.addAll(Arrays.asList(source2));
      queue3.addAll(Arrays.asList(source3));

      Queue<Integer>[] sources = new Queue[4];
      sources[0] = queue0;
      sources[1] = queue1;
      sources[2] = queue2;
      sources[3] = queue3;

      //进行 4 路归并
      ArrayList<Integer> initValues = new ArrayList<>(sources.length);
      for (int i = 0; i < sources.length; i++) {
         initValues.add(sources[i].poll());

      }
      LoserTree<Integer> loserTree=new LoserTree<>(initValues);
      Integer winner=loserTree.getWinner();
      System.out.print(loserTree.getLeaf(winner)+" ");
      while (sources.length>0){
         Integer newLeaf=sources[winner].poll();
         if(newLeaf==null){
            loserTree.del(winner);
         }else {
            loserTree.add(winner,newLeaf);
         }
         winner=loserTree.getWinner();
         if(winner==null){
            break;
         }
         System.out.print(loserTree.getLeaf(winner)+" ");
      }
   }

   public static void main(String[] args) {
      test1();
   }
}
