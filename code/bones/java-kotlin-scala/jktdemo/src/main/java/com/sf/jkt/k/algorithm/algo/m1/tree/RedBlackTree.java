package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.Arrays;

public class RedBlackTree {
    private static final int R = 0;
    private static final int B = 1;
    private static final Node nil = new Node(-1);
    private Node root = nil;

    private static class Node {
        int val;
        int count;
        int color = B;
        Node left = nil;
        Node right = nil;
        Node p = nil;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node [key=" + val + ", color=" + color + ", left=" + left.val + ", right=" + right.val + ", p="
                    + p.val + "]" + "\r\n";
        }
    }

    public void insert(Node node) {
        if (root == nil) {
            root = node;
            root.color=B;
            root.p=nil;
        } else {
            node.color=R;
            Node temp = root;
            while (true) {
                if (temp.val == node.val) {
                    temp.count++;
                } else if (temp.val > node.val) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.p=temp;
                        break;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.p=temp;
                        break;
                    }
                    temp = temp.right;
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(Node node) {
        while (node.p.color == R) {
            Node y;
            if (node.p.p.left == node.p) {
                y = node.p.p.right;
                if (y != nil && y.color == R) {
                    node.p.color = B;
                    y.color = B;
                    node.p.p.color = R;
                    node = node.p.p;
                    continue;
                }
                if (node.p.right == node) {
                    node = node.p;
                    rotateLeft(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateRight(node.p.p);
            } else {
                y = node.p.p.left;
                if (y != nil && y.color == R) {
                    y.color = B;
                    node.p.color = B;
                    node.p.p.color = R;
                    node=node.p.p;
                    continue;
                }
                if (node.p.left == node) {
                    node = node.p;
                    rotateRight(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateLeft(node.p.p);
            }
        }
        root.color=B;
    }

    private void rotateLeft(Node node) {
        if (node.p == nil) {
            Node right = root.right;
            root.right = right.left;
            if (right.left != nil) {
                right.left.p = root;
            }
            root.p = right;
            right.left = root;
            right.p = nil;
            root = right;
        } else {
            if (node.p.left == node) {
                node.p.left = node.right;
            } else {
                node.p.right = node.right;
            }
            node.right.p = node.p;
            node.p = node.right;
            if (node.right.left != nil) {
                node.right.left.p = node;
            }
            node.right = node.right.left;
            node.p.left = node;
        }
    }

    private void rotateRight(Node node) {
        if (node.p == null) {//node=root
            Node left = root.left;
            root.left = left.right;
            if (left.right != nil) {
                left.right.p = root;
            }
            root.p = left;
            left.right = root;
            left.p = nil;
            root = left;
        } else {
            if (node.p.left == node) {
                node.p.left = node.left;
            } else {
                node.p.right = node.left;
            }
            node.left.p = node.p;
            node.p = node.left;
            if (node.left.right != nil) {
                node.left.right.p = node;
            }
            node.left = node.left.right;
            node.p.right = node;
        }
    }


    public void creatTree() {
        int data[] = {23, 32, 15, 221, 3};
        Node node;
        System.out.println(Arrays.toString(data));
        for (int i = 0; i < data.length; i++) {
            node = new Node(data[i]);
            insert(node);
        }
        printTree(root);
    }

    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(node.toString());
        printTree(node.right);
    }





    /**
     * @param args
     */
    public static void main(String[] args) {
        RedBlackTree bst = new RedBlackTree();
        bst.creatTree();

        RedBlackTree1 bst1 = new RedBlackTree1();
        bst1.creatTree();

    }
}
