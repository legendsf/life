package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.Arrays;

public class BinarySortTree1 {
    public Node4 root;

    public void add(Node4 node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void inTravel() {
        if (root != null) {
            root.inTravel(root);
        }
    }

    public void delte(int value) {
        if (root.value == value) {
            root = null;
            return;
        }
        root.delete1(value);
    }

    public Node4 search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }


    public static void test2() {
        Node4 n1 = new Node4(100);
        Node4 n2 = new Node4(80);
        Node4 n3 = new Node4(120);
        Node4 n4 = new Node4(60);
        Node4 n5 = new Node4(90);
        Node4 n6 = new Node4(85);
        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n1.add(n5);
        n1.add(n6);
        n1.levelTravel();
        System.out.println(n1.searchParent(80));
        System.out.println("");
        System.out.println(Arrays.toString(n1.searchMeAndParent(80)));
        System.out.println(Arrays.toString(n1.searchMeAndParent(90)));
        n1.delete(80);
        n1.delete1(80);
        n1.levelTravel();
    }

    public static void test3(){
        Node4 n1 = new Node4(100);
        Node4 n2 = new Node4(80);
        Node4 n3 = new Node4(120);
        Node4 n4 = new Node4(60);
        Node4 n5 = new Node4(90);
        Node4 n6 = new Node4(85);
        BinarySortTree1 bst=new BinarySortTree1();
        bst.add(n1);
        bst.add(n2);
        bst.add(n3);
        bst.add(n4);
        bst.add(n5);
        bst.add(n6);
        System.out.println(bst.search(n3.value));
        bst.delte(80);
        bst.inTravel();
        System.out.println();
        bst.root.levelTravel();
    }

    public static void main(String[] args) {
        test3();


    }

}
