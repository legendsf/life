package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.*;

public class Node2 {
    public int value;
    public int count;
    public Node2 left;
    public Node2 right;

    public Node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String leftstr=left==null?"null":""+left.value;
        String rightstr=right==null?"null":""+right.value;
        return "Node2{" +
                "value=" + value +
                ", count=" + count +
                ", left=" + leftstr +
                ", right=" + rightstr +
                '}';
    }

    public void add(Node2 node){
        if(this.value== node.value){
            this.count++;
        }else if(this.value>node.value){
           if(this.left==null){
               this.left=node;
           }else {
               this.left.add(node);
           }
        }else {
           if(this.right==null){
               this.right=node;
           }else {
               this.right.add(node);
           }
        }
        fixTree();
    }
    public void fixTree(){
        if(this.leftHeight()-this.rightHeight()>1){
            if( this.left!=null&& this.left.leftHeight()-this.left.rightHeight()<0){
                leftRotate(this.left);
            }
            //youxuan
            rightRotate(this);
        }else if(this.rightHeight()-this.leftHeight()>1) {
            if(this.right!=null&& this.right.rightHeight()-this.right.leftHeight()<0){
                rightRotate(this.right);
            }
           //zuoxuan
            leftRotate(this);
        }
    }

    public void leftRotate(Node2 node){
        Node2 newNode=new Node2(node.value);
        newNode.count=node.count;
        newNode.left=node.left;
        newNode.right=node.right.left;
        node.value=node.right.value;
        node.count=node.right.count;
        node.left=newNode;
        node.right=node.right.right;
    }
    public void rightRotate(Node2 node){
        Node2 newNode=new Node2(node.value);
        newNode.count=node.count;
        newNode.right=node.right;
        newNode.left=node.left.right;
        node.value=node.left.value;
        node.count=node.left.count;
        node.left=node.left.left;
        node.right=newNode;
    }
    public int leftHeight(){
        if(this.left==null){
            return 0;
        }
        return this.left.high();
    }
    public int rightHeight(){
        if(this.right==null){
            return 0;
        }
        return this.right.high();
    }
    public int high(){
        return Math.max(this.left==null?0:this.left.high(),this.right==null?0:this.right.high())+1;
    }

    public Node2 searchParent1(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else if(this.right!=null&& this.value<value){
           return this.right.searchParent1(value);
        }else if(this.left!=null&&this.value>value){
            return this.left.searchParent1(value);
        }
        return null;
    }

    public Node2 searchParent(int value){
        if(this.value>value){
            Node2 newNode =this.left;
            if(newNode!=null){
                if(newNode!=null&&newNode.value==value){
                    return this;
                }else {
                    return newNode.searchParent(value);
                }
            }
        }else {
            Node2 newNode =this.right;
            if(newNode!=null){
                if(newNode.value==value){
                    return this;
                }else {
                    return newNode.searchParent(value);
                }
            }
        }
        return null;
    }

    public Node2 searchParent2(int value){
        if(this.value>value){
           Node2 newNode=this.left;
           if(newNode==null){
               return null;
           }
           if(newNode.value==value){
               return this;
           }else {
              return newNode.searchParent2(value);
           }
        }else {
           Node2 newNode=this.right;
           if(newNode==null){
               return null;
           }
           if(newNode.value==value){
               return this;
           }else {
               return newNode.searchParent2(value);
           }
        }
    }
    public List<List<Integer>> levelTravel(){
        List<List<Integer>> ans=new LinkedList<>();
        Node2 root=this;
        Deque<Node2> queue=new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> level=new ArrayList<>();
            for (int i=0;i<size;i++){
                Node2 node=queue.poll();
                System.out.print(node.value+" ");
                level.add(node.value);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
        System.out.println();
        return ans;
    }

    public static void test2(){
        Node2 n1=new Node2(100);
        Node2 n2=new Node2(80);
        Node2 n3=new Node2(120);
        Node2 n4=new Node2(60);
        Node2 n5=new Node2(90);
        Node2 n6=new Node2(85);
        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n1.add(n5);
        n1.add(n6);
        n1.levelTravel();
        System.out.println(n1.searchParent(80));
        System.out.println(n1.searchParent1(80));
        System.out.println(n1.searchParent2(80));
        System.out.println("");
    }

    public static void main(String[] args) {
        test2();
    }
}
