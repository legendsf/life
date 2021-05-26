package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node4 {
    public int value;
    public int count;
    public Node4 left;
    public Node4 right;

    public Node4(int value) {
        this.value = value;
    }
    public Node4 searchParent(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
           return this; 
        }else if(this.left!=null&&this.value>value){
           return this.left.searchParent(value); 
        }else if(this.right!=null&&this.value<value){
           return this.right.searchParent(value); 
        }
        return null;
    }
    public int height(){
       return Math.max(this.left==null?0:this.left.height(),this.right==null?0:this.right.height())+1; 
    }
    
    public int leftHeight(){
       if(this.left==null){
           return 0;
       }
       return this.left.height();
    }
    
    public int rightHeight(){
       if(this.right==null){
           return 0;
       }
       return this.right.height();
    }
    
    public void leftRotate(Node4 node){
       Node4 newNode=new Node4(node.value);
       newNode.count=node.count;
       newNode.left=node.left;
       newNode.right=node.right.left;
       node.value=node.right.value;
       node.count=node.right.count;
       node.right=node.right.right;
       node.left=newNode;
    }
    public void rightRotate(Node4 node){
       Node4 newNode=new Node4(node.value);
       newNode.count=node.count;
       newNode.left=node.left.right;
       newNode.right=node.right;
       node.value=node.left.value;
       node.count=node.left.count;
       node.left=node.left.left;
       node.right=newNode;
    }  
    public void fixTree(){
        if(this.leftHeight()-this.rightHeight()>1){
            if(this.left!=null&&this.left.leftHeight()-this.left.rightHeight()<0){
                leftRotate(this.left);
            }
            rightRotate(this);
        }else if(this.rightHeight()-this.leftHeight()>1){
            if(this.right!=null&&this.right.leftHeight()-this.right.rightHeight()>0){
                rightRotate(this.right);
            }
            leftRotate(this); 
        }
        
    }
    public void add(Node4 node){
       if(this.value==node.value){
           count++;
       } else if(this.value>node.value){
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
    
    @Override
    public String toString() {
        String le=left==null?"null":left.value+"";
        String ri=left==null?"null":right.value+"";
        return "Node4{" +
                "value=" + value +
                ", count=" + count +
                ", left=" + le +
                ", right=" + ri +
                '}';
    }

    public void inTravel(Node4 node){
        if(node==null){
            return;
        }
        inTravel(node.left);
        for (int i=0;i<=node.count;i++){
            System.out.print(node.value+" ");
        }
        inTravel(node.right);
    }

    public Node4 search(int value){
        if(this.value==value){
            return this;
        }else if(this.left!=null&&this.value>value){
           return this.left.search(value);
        }else if(this.right!=null&&this.value<value){
           return this.right.search(value);
        }
        return null;
    }

    /**
     *
     * @param value
     * @return
     */
    public Node4[] searchMeAndParent(int value){

         if(this.value==value){
             return new Node4[]{this,null};
         }else if(this.value>value&&this.left!=null){
             if(this.left.value==value){
                 return new Node4[]{this.left,this};
             }else {
                 return this.left.searchMeAndParent(value);
             }
         }else if(this.value<value&&this.right!=null){
             if(this.right.value==value){
                 return new Node4[]{this.right,this};
             }else {
                 return this.right.searchMeAndParent(value);
             }
         }
        return null;
    }

    public void delete(int value){
        Node4[] cp=searchMeAndParent(value);
        if(cp==null){
            return;
        }
        Node4 target=cp[0];
        Node4 parent=cp[1];
        if(parent==null&&target.left==null&&target.right==null){
            this.value=0;
            this.count=0;
            return;
        }
        if(target.right==null&&target.left==null){
            if(target.value== parent.left.value){
                parent.left=null;
            }else {
                parent.right=null;
            }
        }else if(target.right!=null&&target.left!=null){
           Node4 node=deleteMin(target.right);
           target.value=node.value;
           target.count=node.count;
        }else {
            if(target.left!=null){
               if(parent.left==target){
                   parent.left=target.left;
               } else {
                   parent.right=target.left;
               }
            }else {
               if(parent.left==target){
                   parent.left=target.right;
               } else {
                   parent.right=target.right;
               }
            }
        }


    }

    public Node4 deleteMin(Node4 node){
        Node4 target=node;
        while (target.left!=null){
            target=target.left;
        }
        delete(target.value);
        return target;
    }

    public Node4 deleteMin1(Node4 node){
        Node4 target=node;
        while (target.left!=null){
            target=target.left;
        }
        delete1(target.value);
        return target;
    }

    public void delete1(int value){
        Node4[] nodes=searchMeAndParent(value);
        if(nodes==null){
            return;
        }
        Node4 target=nodes[0],parent=nodes[1];
        if(parent==null&&target.left==null&&target.right==null){
            target.value=0;
            target.count=0;
            return;
        }
        if(target.left==null&&target.right==null){
            if(parent.left==target){
                parent.left=null;
            }else {
                parent.right=null;
            }
            return;
        }
        if(target.left!=null&&target.right!=null){
            Node4 node=deleteMin(target.right);
            target.value=node.value;
            target.count=node.count;
        }else {
            if(target.left!=null){
               if(parent.left==target){
                   parent.left=target.left;
               }else {
                   parent.right=target.left;
               }
            }else {
               if(parent.left==target.right){
                   parent.left=target.right;
               }else {
                   parent.right=target.right;
               }
            }
        }
    }

    public List<List<Integer>> levelTravel(){
        List<List<Integer>> ans=new ArrayList<>();
        ArrayDeque<Node4> deque=new ArrayDeque();
        deque.offer(this);
        while (!deque.isEmpty()){
            int size=deque.size();
            List<Integer> level=new ArrayList<>();
            ans.add(level);
            for (int i=0;i<size;i++){
                Node4 node=deque.poll();
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
        Node4 n1=new Node4(100);
        Node4 n2=new Node4(80);
        Node4 n3=new Node4(120);
        Node4 n4=new Node4(60);
        Node4 n5=new Node4(90);
        Node4 n6=new Node4(85);
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

    public static void main(String[] args) {
        test2();
    }


}
