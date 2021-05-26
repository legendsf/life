package com.sf.jkt.k.algorithm.algo.m1.tree;

public class TreeNode {
  public   int val;
  public   TreeNode left;
  public   TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        String left=this.left==null?"":""+this.left.val;
        String right=this.right==null?"":""+this.right.val;

        return "TreeNode{" +
                "val=" + val +
                ", left=" + left+
                ", right=" + right+
                '}';
    }
}
