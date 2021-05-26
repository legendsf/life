package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.LinkedList;
import java.util.List;

public class NodeDemo {
    public static class Node{
        int data;
        Node parent;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();


        recur(root, sum,path,res);
        return res;
    }

   public static void recur(TreeNode root, int tar, LinkedList<Integer> path, LinkedList<List<Integer>> res) {
        if(root == null) {return;}
        path.add(root.val);
        tar -= root.val;
        if(tar == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList(path));
        }
        recur(root.left, tar,path,res);
        recur(root.right, tar,path,res);
        path.removeLast();
    }

    public static List<List<Integer>> pathSum2(TreeNode root,int sum){
        LinkedList<List<Integer>> res=new LinkedList<>();
        LinkedList<Integer> path=new LinkedList<>();
        recur2(root,sum,path,res);
        return res ;
    }
    public static void recur2(TreeNode root,int sum,LinkedList<Integer> path,LinkedList<List<Integer>> res){
        if(root==null){
            return;
        }
        path.add(root.val);
        sum -=root.val;
        if(sum==0 &&root.left==null&&root.right==null){
            res.add(new LinkedList<>(path));
        }
        recur(root.left,sum,path,res);
        recur(root.right,sum,path,res);
        path.removeLast();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
