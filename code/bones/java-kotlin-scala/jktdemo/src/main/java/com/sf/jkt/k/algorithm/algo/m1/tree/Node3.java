package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Node3 {
    public int value;
    public int count;
    public Node3 left;
    public Node3 right;

    public Node3(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String ls=left==null?"null":""+left.value;
        String rs=right==null?"null":""+right.value;
        return "Node3{" +
                "value=" + value +
                ", count=" + count +
                ", left=" + ls +
                ", right=" + rs +
                '}';
    }
    
    public Node3 searchParent(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else if(this.left!=null&&this.value>value){
            return this.left.searchParent(value);
        }else if(this.right!=null&&this.value<value){
           return this.right.searchParent(value); 
        }
        return null; 
    }
    public int high(){
        return Math.max(this.left==null?0:this.left.high(),this.right==null?0:this.right.high())+1;
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

    public void add(Node3 node){
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

    public void leftRotate(Node3 node){
        Node3 newNode=new Node3(node.value);
        newNode.count=node.count;
        newNode.left=node.left;
        newNode.right=node.right.left;
        node.value=node.right.value;
        node.count=node.right.count;
        node.left=newNode;
        node.right=node.right.right;
    }
    public void rightRotate(Node3 node){
        Node3 newNode=new Node3(node.value);
        newNode.count=node.count;
        newNode.right=node.right;
        newNode.left=node.left.right;
        node.value=node.left.value;
        node.count=node.left.count;
        node.left=node.left.left;
        node.right=newNode;
    }

    public List<List<Integer>> levelTravel(){
        List<List<Integer>> ans=new ArrayList<>();
        ArrayDeque<Node3> deque=new ArrayDeque();
        deque.offer(this);
        while (!deque.isEmpty()){
            int size=deque.size();
            List<Integer> level=new ArrayList<>();
            ans.add(level);
            for (int i=0;i<size;i++){
               Node3 node=deque.poll();
               level.add(node.value);
                System.out.print(node.value+" ");
                if(node.left!=null){
                    deque.offer(node.left);
                }
                if(node.right!=null){
                    deque.offer(node.right);
                }
            }
            System.out.println();
        }
        return ans;
    }

    public static void test2(){
        Node3 n1=new Node3(100);
        Node3 n2=new Node3(80);
        Node3 n3=new Node3(120);
        Node3 n4=new Node3(60);
        Node3 n5=new Node3(90);
        Node3 n6=new Node3(85);
        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n1.add(n5);
        n1.add(n6);
        n1.levelTravel();
        System.out.println(n1.searchParent(80));
        System.out.println("");
    }

    public static void main(String[] args) {
        test2();
    }
    
}
