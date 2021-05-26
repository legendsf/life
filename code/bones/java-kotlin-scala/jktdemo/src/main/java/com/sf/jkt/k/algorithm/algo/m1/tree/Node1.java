package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Node1 {
   public  int value;
   public int count;
   Node1 left;
   Node1 right;

   public Node1(int value) {
      this.value = value;
   }

   public void add(Node1 node1){
      if(node1==null){
         return;
      }
      if(this.value> node1.value){
        if(this.left==null){
           this.left=node1;
        }else {
           this.left.add(node1);
        }
      }else {
        if(this.right==null){
           this.right=node1;
        }else {
           this.right.add(node1);
        }
      }
     fixTree();
   }

   public void fixTree(){
        if(this.leftHeight()-this.rightHeight()>1){
            //youxuan
            if(this.left!=null&&this.left.leftHeight()-this.left.rightHeight()<0){
                leftRotate(this.left);
            }
            rightRotate(this);
        }else if(this.rightHeight()-this.leftHeight()>1) {
            //zuoxuan
            if(this.right!=null&&this.right.rightHeight()-this.right.leftHeight()<0){
               //youxuan
                rightRotate(this.right);
            }
            leftRotate(this);
        }
   }

   public Node1 searchParent1(int value){
      if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
         return this;
      }
      if(this.left!=null&&value<this.left.value){
        return this.left.searchParent1(value);
      }else if(this.right!=null&&value>this.right.value) {
        return this.right.searchParent1(value);
      }
      return null;
   }

   public Node1 searchParent(int value){
      if(this.value>value){
        Node1 newNode=this.left;
        if(newNode==null){
           return null;
        }
        if(newNode.value==value){
            return this;
        }
        return newNode.searchParent(value);
      }else {
        Node1 newNode =this.right;
        if(newNode==null){
            return null;
        }
        if(newNode.value==value){
           return this;
        }
        return newNode.searchParent(value);
      }
   }

   public void leftRotate(Node1 node1){
       Node1 newNode=new Node1(node1.value);
       newNode.count=node1.count;
       newNode.left=node1.left;
       newNode.right=node1.right.left;
       node1.value=node1.right.value;
       node1.count=node1.right.count;
       node1.left=newNode;
       node1.right=node1.right.right;
   }

   public void rightRotate(Node1 node1){
       Node1 newNode=new Node1(node1.value);
       newNode.count=node1.count;
       newNode.right=node1.right;
       newNode.left=node1.left.right;
       node1.value=node1.left.value;
       node1.count=node1.left.count;
       node1.left=node1.left.left;
       node1.right=newNode;
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

   public List<List<Integer>> levelTravel(){
       List<List<Integer>> ans=new ArrayList<>();
      Node1 cur=this;
      Deque<Node1> queue=new ArrayDeque<>();
      queue.add(cur);
      while (!queue.isEmpty()){
          int size=queue.size();
          List<Integer> level=new ArrayList<>();
          ans.add(level);
          for(int i=0;i<size;i++){
              Node1 node=queue.poll();
              level.add(node.value);
              System.out.print(node.value+" ");
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
        Node1 n1=new Node1(100);
        Node1 n2=new Node1(80);
        Node1 n3=new Node1(120);
        Node1 n4=new Node1(60);
        Node1 n5=new Node1(90);
        Node1 n6=new Node1(85);
        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n1.add(n5);
        n1.add(n6);
        n1.levelTravel();
        System.out.println("");
    }

   public static void main(String[] args) {
        test2();
   }
}
