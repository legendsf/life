package com.sf.jkt.k.algorithm.study.zhaoyun.c11;

import java.util.Arrays;

public class RedBlackTree1 {
    private static int R=0;
    private static int B=1;
   public static class Node{
        int key=-1;
        int color=B;
        Node left=nil;
        Node right=nil;
        Node p=nil;

        public Node(int key) {
            this.key = key;
        }
        @Override
        public String toString() {
            return "Node [key=" + key + ", color=" + color + ", left=" + left.key + ", right=" + right.key + ", p="
                    + p.key + "]" + "\r\n";
        }
    }
    static final Node nil=new Node(-1);
    Node root=nil;

    public void printTree(Node node){
        if(node==nil){
            return;
        }
        printTree(node.left);
        System.out.print(node.toString()+"  ");
        printTree(node.right);
    }
    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = B;
            node.p = nil;
        } else {
            node.color = R;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }
    
    public void insert1(Node node){
        Node temp=root;
        if(root==nil){
            root=node;
            node.color=B;
            node.p=nil;
        }else {
            node.color=R;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree1(node);
        }
    }
    private void fixTree(Node node) {
        while (node.p.color == R) {
            Node y = nil;
            if (node.p == node.p.p.left) {
                y = node.p.p.right;

                if (y != nil && y.color == R) {
                    node.p.color = B;
                    y.color = B;
                    node.p.p.color = R;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.right) {
                    node = node.p;
                    rotateLeft(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateRight(node.p.p);
            } else {
                y = node.p.p.left;
                if (y != nil && y.color == R) {
                    node.p.color = B;
                    y.color = B;
                    node.p.p.color = R;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.left) {
                    node = node.p;
                    rotateRight(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateLeft(node.p.p);
            }
        }
        root.color = B;
    }

    public void fixTree1(Node node){
        while (node.p.color==R){
            Node y=nil;
            if(node.p==node.p.p.left){
                y=node.p.p.right;
                if(y!=nil&&y.color==R){
                   node.p.color=B;
                   y.color=B;
                   node.p.p.color=R;
                   node=node.p.p;
                   continue;
                }
                if(node==node.p.right){
                    node=node.p;
                    rotateLeft1(node);
                }
                node.p.color=B;
                node.p.p.color=R;
                rotateRight1(node.p.p);
            }else {
                y=node.p.p.left;
                if(y!=nil&&y.color==R){
                    node.p.color=B;
                    y.color=B;
                    node.p.p.color=R;
                    node=node.p.p;
                    continue;
                }
                if(node==node.p.left){
                    node=node.p;
                    rotateRight1(node);
                }
                node.p.color=B;
                node.p.p.color=R;
                rotateLeft1(node.p.p);
            }
        }
        root.color=B;
    }

    void rotateLeft(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
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
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.p = root;
            root.p = right;
            right.left = root;
            right.p = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
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
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.p = root;
            root.p = left;
            left.right = root;
            left.p = nil;
            root = left;
        }
    }


    public void rotateLeft1(Node node){
        if(node.p!=nil){
            if(node==node.p.left){
               node.p.left=node.right;
            }else {
                node.p.right=node.right;
            }
            node.right.p=node.p;
            node.p=node.right;
            if(node.right.left!=nil){
                node.right.left.p=node;
            }
            node.right=node.right.left;
            node.p.left=node;
        }else {
            Node right=root.right;
            root.right=right.left;
            right.left.p=root;
            root.p=right;
            right.left=root;
            right.p=nil;
            root=right;
        }
    }

    public void rotateRight1(Node node){
        if(node.p!=nil){
            if(node==node.p.left){
                node.p.left=node.left;
            }else {
                node.p.right=node.left;
            }
            node.left.p=node.p;
            node.p=node.left;
            if(node.left.right!=nil){
                node.left.right.p=node;
            }
            node.left=node.left.right;
            node.p.right=node;
        }else {
            Node left=root.left;
            root.left=left.right;
            left.right.p=root;
            root.p=left;
            left.right=root;
            left.p=nil;
            root=left;
        }
    }

    public void createTree(){
        int[] data={23,32,15,221,3};
        Node node;
        System.out.println(Arrays.toString(data));
        for(int i=0;i<data.length;i++){
            node=new Node(data[i]);
            insert1(node);
        }
        printTree(root);
    }

    public static void main(String[] args) {
        RedBlackTree1 tree=new RedBlackTree1();
        tree.createTree();
    }

}
