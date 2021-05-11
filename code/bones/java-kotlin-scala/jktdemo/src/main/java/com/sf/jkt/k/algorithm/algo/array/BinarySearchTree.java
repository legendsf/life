package com.sf.jkt.k.algorithm.algo.array;


public class BinarySearchTree {
    public int data;
    public BinarySearchTree left;
    public BinarySearchTree right;

    public BinarySearchTree(int data) {
        this.data = data;
    }

    public void  insert(BinarySearchTree root,int data){
        if(root.data<data){//右边大于
            if(root.right==null){
                root.right=new BinarySearchTree(data);
            }else {
                insert(root.right,data);
            }
        }else {//小于等于在左边
            if(root.left==null){
                root.left=new BinarySearchTree(data);
            }else {
                insert(root.left,data);
            }
        }
    }

    public void find(BinarySearchTree root,int data){
        if(root==null){
            System.out.println("找不到");
            return;
        }
        if(root.data<data){
            find(root.right,data);
        }else if(root.data>data) {
            find(root.left,data);
        }else {
            System.out.println("找到了");
            System.out.println(root.data);
            return;
        }
    }

    public void  delete(){

    }

}
