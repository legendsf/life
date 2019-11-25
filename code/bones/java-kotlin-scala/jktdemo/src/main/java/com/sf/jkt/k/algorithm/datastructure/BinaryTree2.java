package com.sf.jkt.k.algorithm.datastructure;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree2 {
    public class Node {
        public Integer data;
        public Node left;
        public Node right;

        public Node(Integer data) {
            this.data = data;
        }

        public int size(Node node) {
            if (node == null) {
                return 0;
            } else {
                return size(node.left) + size(node.right) + 1;
            }
        }


        public void addNode(Node newNode) {
            if (newNode.data.compareTo(this.data) < 0) {
                if (this.left == null) {
                    this.left = newNode;
                } else {
                    this.left.addNode(newNode);
                }

            } else {
                if (this.right == null) {
                    this.right = newNode;
                } else {
                    this.right.addNode(newNode);
                }
            }
        }

        public void midPrint() {
            if (this.left != null) {
                this.left.midPrint();
            }
            System.out.println(this.data + "\t");
            if (this.right != null) {
                this.right.midPrint();
            }
        }

    }

    private Node root;

    public void add(Integer data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
        } else {
            root.addNode(newNode);
        }
    }

    public static void main(String[] args) {
        BinaryTree2 bt = new BinaryTree2();
        bt.add(4);
        bt.add(7);
        bt.add(9);
        bt.add(5);
        bt.root.midPrint();
    }

}

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    private int target;

    public List<List<Integer>> pathSum(BinaryTree2.Node root, int sum) {
        this.target = sum;
        helper(root, 0, new ArrayList<Integer>());
        return result;
    }

    private void helper(BinaryTree2.Node node, int currentSum, List<Integer> currentList) {
        if (node == null) {
            return;
        }
        currentSum = currentSum + node.data;
        currentList.add(node.data);
        if (node.left == null && node.right == null) {
            if (node.left == null && node.right == null) {
                result.add(currentList);
            }
            currentList.remove(currentList.size() - 1);
            return;
        }
        helper(node.left, currentSum, currentList);
        helper(node.right, currentSum, currentList);
        currentList.remove(currentList.size() - 1);
    }
}