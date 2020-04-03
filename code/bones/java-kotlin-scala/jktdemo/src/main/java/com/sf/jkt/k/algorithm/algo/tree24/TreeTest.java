package com.sf.jkt.k.algorithm.algo.tree24;

import java.util.ArrayList;

public class TreeTest {

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target, ArrayList<Integer> list,
                                                         ArrayList<ArrayList<Integer>> result) {
        if (root == null) {
            return result;
        }
        target = target - root.val;//减去根的值，一直减到target为0
        list.add(root.val);
        if (root.left == null && root.right == null) {//到叶子结点
            if (target == 0) {//target 等于0说明从根结点到叶子结点之和恰巧为target
                result.add(list);
            }
        }
        findPath(root.left, target, list, result);
        findPath(root.right, target, list, result);
        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
