package com.sf.jkt.k.algorithm.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 根节点的父节点为最小值的败者树，非叶子节点保存左右子树上传的值中的较大值（败者），较小值继续向上传递
 * 完全二叉树 k路归并k个叶节点，k-1个非叶子节点+1个根节点的父节点
 *  int[] initValues={2,4,5,3}
 *         tree:
 *
 *         ls[0]=0                                    initValues[0]=2
 *
 *         ls[1]=3        -----> index=ls[x] --->     initValues[3]=3
 *
 * ls[2]=1         ls[3]=2                   initValues[1]=4             initValue[2]=5;
 * 头结点是2，是胜者 最小值为胜
 */
public class LoserTree<T extends Comparable> {
    /**
     * 败者树（不包括叶子节点），保存下标    	tree[0]保存最小值下标
     */
    private Integer[] tree = null;
    /**
     * 叶子节点数组的长度（即 k 路归并中的 k）
     */
    private int size = 0;
    /**
     * 叶子节点（必须是可以比较的对象） 
     */
    private ArrayList<T> leaves = null;
    /**
     * 初始化的最小值
     */
    private static final Integer MIN_KEY = -1;
 
    /**
     * 败者树构造函数
     *
     * @param initValues 初始化叶子节点的数组，即各个归并段的首元素组成的数组
     */
    public LoserTree(ArrayList<T> initValues) {
        this.leaves = initValues;
        this.size = initValues.size();
        this.tree = new Integer[size];
 
        //初始化败者树（严格的说，此时它只是一个普通的二叉树）
        for (int i = 0; i < size; i++) {
            //初始化时，树中各个节点值设为可能的最小值
            tree[i] = MIN_KEY;
        }
        //初始化 要从最后一个节点开始调整  k次调整   
        for (int i = size - 1; i >= 0; i--) {
            adjust(i);
        }
 
    }
 
    /**
     * 从底向上调整树结构
     *
     * @param s 叶子节点数组的下标
     */
    private void adjust2(int s) {
        // tree[t] 是 leaves[s] 的父节点       
        int t = (s + size) / 2;
        while (t > 0) {
        									//如果叶子节点值大于父节点（保存的下标）指向的值
            if (s >= 0 && (tree[t] == -1 || leaves.get(s).compareTo(leaves.get(tree[t])) > 0)) {
                //父节点保存其下标：总是保存较大的（败者）。 	较小值的下标（用s记录）->向上传递
                int temp = s;
                s = tree[t];
                tree[t] = temp;
            }
            // tree[Integer/2] 是 tree[Integer] 的父节点
            t /= 2;
        }
        //最后的胜者（最小值）
        tree[0] = s;
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

    public void  adjust1(int s) {
        int t = (s + size) / 2;
        while (t > 0) {
            if (s >= 0 && (tree[t] == -1 || leaves.get(s).compareTo(leaves.get(tree[t])) > 0)) {
                int temp = s;
                s = tree[t];
                tree[t] = temp;
            }
            t /= 2;
        }
        tree[0] = s;
    }

        /**
         * 给叶子节点赋值
         *
         * @param leaf 叶子节点值
         * @param s    叶子节点的下标
         */
    public void add(T leaf, int s) {
        leaves.set(s, leaf);
        //每次赋值之后，都要向上调整，使根节点保存最小值的下标（找到当前最小值）
        adjust(s);
    }
 
    /**
     * 删除叶子节点，即一个归并段元素取空
     *
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
    public T getLeaf(int s) {
        return leaves.get(s);
    }
 
    /**
     * 获得胜者(值为最终胜出的叶子节点的下标)
     *
     * @return
     */
    public Integer getWinner() {
        return tree.length > 0 ? tree[0] : null;
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
        LoserTree<Integer> loserTree = new LoserTree(initValues);
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
 
    public static void main(String[] args) {
        test1();
    }
}