package com.sf.jkt.k.algorithm.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LoserTree1<T extends Comparable> {
    Integer[] tree;//
    int size=0;
    ArrayList<T> leaves;
    int MIN_KEY=-1;

    public LoserTree1(ArrayList<T> initValues) {
        this.size = initValues.size();
        leaves=initValues;
        tree=new Integer[size];
        for(int i=size-1;i>=0;i--){
            tree[i]=MIN_KEY;
        }
        for(int i=size-1;i>=0;i--){
            adjust(i);
        }
    }

    public  void  adjust(int s){ //s代表上一轮的胜者
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

    /**
     * 给叶子节点赋值
     *
     * @param leaf 叶子节点值
     * @param s    叶子节点的下标
     */
    public void add1(T leaf, int s) {
        leaves.set(s, leaf);
        //每次赋值之后，都要向上调整，使根节点保存最小值的下标（找到当前最小值）
        adjust(s);
    }

    /**
     * 删除叶子节点，即一个归并段元素取空

     * @param s 叶子节点的下标
     */
    public void del(int s) {
        //删除叶子节点
        leaves.remove(s);
        this.size--;
        this.tree = new Integer[size];

        //初始化败者树（严格的说，此时它只是一个普通的二叉树）
        for (int i = 0; i < size; i++) {
            //初始化时，树中各个节点值设为可能的最小值
            tree[i] = MIN_KEY;
        }
        //从最后一个节点开始调整
        for (int i = size - 1; i >= 0; i--) {
            adjust(i);
        }

    }

    /**
     * 根据下标找到叶子节点（取值）
     *
     * @param s 叶子节点下标
     * @return
     */
    public T getLeaf1(int s) {
        return leaves.get(s);
    }




    public void adjust1(int index){//index 代表上一次胜出的索引
        int p=(index+size)/2;//最下面一层叶子和initValues 的对应关系，其他层的父亲为 p=p/2;
        while (p>0){//最多到p[1] ls[0]存放了胜者,p代表losertree的某个层
           if(index>=0){//如果上一次胜出的索引是有效的
              if(tree[p]==-1 || leaves.get(index).compareTo(leaves.get(tree[p]))>0){
                  //当前index 对应的值比 父亲结点的index对应的值大,那么要交换，同时index 保存，上一次胜出的值对应的index
                  int temp=index;//本次的败者
                  index=tree[p];//上一轮的胜者
                  tree[p]=temp;//更新父亲的败者，
              }
           }
           p /=2;//进行下一次循环
        }
        tree[0]=index;//更新最终胜者
    }

    /**
     * 获得胜者(值为最终胜出的叶子节点的下标)
     *
     * @return
     */
    public Integer getWinner1() {
        return tree.length > 0 ? tree[0] : null;
    }

    public Integer getWinner(){
        return getWinner1();
//        return tree.length>0?tree[0]:null;
//        return tree.length > 0 ? tree[0] : null;
    }

    public void del1(int winner){
        leaves.remove(winner);
        size=size-1;
        tree=new Integer[size];
        for(int i=0;i<size;i++){
            tree[i]=MIN_KEY;
        }
        for(int i=size-1;i>=0;i--){
            adjust(i);
        }
    }

    public T getLeaf(int index){
        return leaves.get(index);
    }

    public void  add(T newleaf,int winner){
       leaves.set(winner,newleaf);
       adjust(winner);
    }

    public static void test1(){
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
        //初始化败者树
        LoserTree1<Integer> loserTree = new LoserTree1(initValues);
        //输出胜者
        Integer s = loserTree.getWinner();
        System.out.print(loserTree.getLeaf(s) + " ");
        while (sources.length > 0) {
            //新增叶子节点
            Integer newLeaf = sources[s].poll();
            if (newLeaf == null) {
                // sources[s] 对应的队列（归并段）已经为空，删除队列并调整败者树
                loserTree.del(s);
            } else {
                loserTree.add(newLeaf, s);
            }

            s = loserTree.getWinner();
            if (s == null) {
                break;
            }
            //输出胜者
            System.out.print(loserTree.getLeaf(s) + " ");
        }
    }

    public static void test(){
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
        for(int i=0;i<initValues.size();i++){
            initValues.add(sources[i].poll());
        }
        LoserTree1<Integer> loserTree1=new LoserTree1<>(initValues);
        Integer winner=loserTree1.getWinner();
        System.out.println(loserTree1.getLeaf(winner));
        while (sources.length>0){//队列不空说明还有数据
            Integer newLeaf=sources[winner].poll();
            if(newLeaf==null){
               loserTree1.del(winner);//说明这个队列空了
            }else {
                loserTree1.add(newLeaf,winner);//继续加入树中并调整树
            }
            winner=loserTree1.getWinner();
            if(winner==null){
                break;//说明已经处理完了
            }
            System.out.println(loserTree1.getLeaf(winner));
        }
    }

    public static void main(String[] args) {
        test1();
    }

}
