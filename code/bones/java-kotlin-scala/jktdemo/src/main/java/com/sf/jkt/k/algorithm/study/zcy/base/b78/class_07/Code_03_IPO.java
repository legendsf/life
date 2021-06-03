package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.liuyixiang.com/post/13325.html
 * <p>
 * 输入: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 * <p>
 * 输出: 4
 */
public class Code_03_IPO {
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }

    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                System.out.println(W);
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        System.out.println(W);
        return W;
    }

    public static int findMaximizedCapital1(int k,int w,int[] profits,int[] capital){
        PriorityQueue<Integer[]> minCapitalHeap=new PriorityQueue<>((p1,p2)->{
            return p1[1]-p2[1];//小capital的放前面
        });
       Integer[][] project=new Integer[profits.length][2];
       for (int i=0;i<profits.length;i++){
           project[i][0]=profits[i];
           project[i][1]=capital[i];
           minCapitalHeap.add(project[i]);
       }

       PriorityQueue<Integer[]> maxProfitHeap=new PriorityQueue<>((p1,p2)->{
          return p2[0]-p1[0];//收益能够获取大资金的放前面
       });
       for (int i=0;i<k;i++){
           while (!minCapitalHeap.isEmpty()&&minCapitalHeap.peek()[1]<=w){
               maxProfitHeap.offer(minCapitalHeap.poll());
           }
           if (maxProfitHeap.isEmpty()){
               System.out.println(w);
               return w;
           }
           w+=maxProfitHeap.poll()[0];
       }
        System.out.println(w);
       return w;
    }

    public static int findMaximizedCapital2(int k,int w,int[]profits,int[]cost){
        Integer[][]project=new Integer[profits.length][2];//0-profit,1-cost
        PriorityQueue<Integer[]> minCost=new PriorityQueue<Integer[]>((p1,p2)->{
           return p1[1].equals(p2[1])?p2[0]-p1[0]:p1[1]-p2[1];
        });
        for (int i=0;i<profits.length;i++){
            project[i][0]=profits[i];
            project[i][1]=cost[i];
            minCost.add(project[i]);
        }
        PriorityQueue<Integer[]> maxProfit=new PriorityQueue<Integer[]>((p1,p2)->{
            return p1[0].equals(p2[0])?p1[1]-p2[1]:p2[0]-p1[0];
        });
        for (int i=0;i<k;i++){
            while (!minCost.isEmpty()&&minCost.peek()[1]<=w){
                Integer[] proj=minCost.poll();
                maxProfit.add(proj);
            }
            if (maxProfit.isEmpty()){
                System.out.println(w);
                return w;
            }
            w += maxProfit.poll()[0];

        }
        System.out.println(w);
        return w    ;

    }
    public static int findMaximizedCapital3(int k,int w,int[]profits,int[]cost){
       Integer[][]project=new Integer[profits.length][2];//0-profit,1-cost
        PriorityQueue<Integer[]> minCost=new PriorityQueue<Integer[]>((p1,p2)->{
            return p1[1].equals(p2[1])?p2[0]-p1[0]:p1[1]-p2[1];
        });
        PriorityQueue<Integer[]> maxProfit=new PriorityQueue<Integer[]>((p1,p2)->{
            return p1[0].equals(p2[0])?p1[1]-p2[1]:p2[0]-p1[0];
        });
        for (int i=0;i<profits.length;i++){
            project[i][0]=profits[i];
            project[i][1]=cost[i];
            minCost.add(project[i]);
        }
        for (int i=0;i<k;i++){
            while (!minCost.isEmpty()&&minCost.peek()[1]<=w){
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()){
                System.out.println(w);
                return w;
            }
            w += maxProfit.poll()[0];
        }
        System.out.println(w);
        return w;
    }

    public static void test1() {
        int k = 2, W = 0;
        int[] Profits = {1, 2, 3}, Capital = {0, 1, 1};
        findMaximizedCapital(k,W,Profits,Capital);
        W=0;
        System.out.println("--");
        findMaximizedCapital1(k,W,Profits,Capital);
        findMaximizedCapital2(k,W,Profits,Capital);
        findMaximizedCapital3(k,W,Profits,Capital);
    }

    public static void main(String[] args) {
        test1();
    }

}
