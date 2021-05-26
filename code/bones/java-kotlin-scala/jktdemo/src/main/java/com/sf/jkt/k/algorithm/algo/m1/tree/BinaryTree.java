package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.*;

public class BinaryTree {
    public static class Node{
        String data;
        Node left;
        Node right;

        public Node(String data) {
            this.data = data;
        }
    }
    /**
     * 构建
     * 遍历
     *  前中后层 morris
     *  判断平衡树
     * 求前驱后继
     * 增加结点
     * 左旋右旋
     * 删除结点
     *
     */
    public static Node createBinaryTree(){
        Node a=new Node("a");
        Node b=new Node("b");
        Node c=new Node("c");
        Node d=new Node("d");
        Node e=new Node("e");
        Node g=new Node("g");
        a.left=b;
        a.right=c;
        b.left=d;
        b.right=e;
        c.left=g;
        return a;
    }

    public static void preTravel(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.data+" ");
        if(node.left!=null){
            preTravel(node.left);
        }
        if(node.right!=null){
            preTravel(node.right);
        }
    }

    public static void midTravel(Node node){
        if(node==null){
            return;
        }
        if(node.left!=null){
            midTravel(node.left);
        }
        System.out.print(node.data+" ");
        if(node.right!=null){
            midTravel(node.right);
        }
    }
    public static void postTravel(Node node){
        if(node==null){
            return;
        }
        if(node.left!=null){
            postTravel(node.left);
        }
        if(node.right!=null){
            postTravel(node.right);
        }
        System.out.print(node.data+" ");
    }

    public static void levelTravel(Node root){
        if(root==null){
            return;
        }
        Deque<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
           Node node=queue.poll();
            System.out.print(node.data+" ");
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    public static List<List<String>> levelTravel1(Node root){
        List<List<String>> result=new ArrayList<>();
        if(root==null){
            return  result;
        }
        List<Node> priviousLayer = Arrays.asList(root);
        while (!priviousLayer.isEmpty()){
            List<Node> currentLayer=new ArrayList<>();
            List<String> priviousVals=new ArrayList<>();
            for (Node node: priviousLayer){
                System.out.print(node.data+" ");
                priviousVals.add(node.data);
                if(node.left!=null){
                    currentLayer.add(node.left);
                }
                if(node.right!=null){
                    currentLayer.add(node.right);
                }
            }
            result.add(priviousVals);
            priviousLayer=currentLayer;
        }
        System.out.println();
        return result;
    }

    public static List<List<String>> levelTravel2(Node root){
        List<List<String>>result=new ArrayList<>();
        if(root==null){
            return result;
        }
        levelTravel2(root,0,result);
        result.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return result;
    }
    public static void levelTravel2(Node node,int level,List<List<String>> result){
        if(node==null){
            return;
        }
        if(result.size()<=level){
            result.add(new ArrayList<String>());
        }
        result.get(level).add(node.data);
        levelTravel2(node.left,level+1,result);
        levelTravel2(node.right,level+1,result);
    }

    public static Node findMostLeft(Node node){
        if(node==null){
            return node;
        }
        while (node.left!=null){
            node=node.left;
        }
        System.out.println(node.data);
        return node;
    }

    public static Node findMostRight(Node node){
        if(node==null){
            return node;
        }
        while (node.right!=null){
            node=node.right;
        }
        System.out.println(node.data);
        return node;
    }

    public static void Morris(Node head){
        if(head==null){
            return;
        }
        Node cur=head;
        Node mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else {
                    mostRight.right=null;
                }
            }
            cur=cur.right;
        }
    }

    public static void morrisIn(Node head){
        if(head==null){
            return;
        }
        Node cur=head;
        Node mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&& mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                   mostRight.right=cur;
                   cur=cur.left;
                   continue;
               }else {
                   mostRight.right=null;
               }
            }
            System.out.print(cur.data+" ");
            cur=cur.right;
        }
    }

    public static void morrisPre(Node head){
        if(head==null){
            return;
        }
        Node cur=head;
        Node mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                  mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                   System.out.print(cur.data+" ");
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
               }
            }else {
                System.out.print(cur.data+" ");
            }
            cur=cur.right;
        }
    }

    public static void morrisPost(Node head){
        if(head==null){
            return;
        }
        Node cur=head;
        Node mostRight=null;
        while (cur!=null){

            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
                   printEdge(cur.left);
               }
            }
//            System.out.print(cur.data+" ");
            cur=cur.right;

        }

        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node node){
        if(node==null){
            return;
        }
        Node tail=reverseEdge(node);
        Node cur=tail;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.right;
        }
        reverseEdge(tail);
    }
    public static Node reverseEdge(Node node){
        if(node==null){
            return node;
        }
        Node prev=null;
        Node cur=node;
        while (cur!=null){
            Node next=cur.right;
            cur.right=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }

    public static void morrisPost1(Node node){
        if(node==null){
            return;
        }
        Node cur=node,mostRight=null;
        while (cur!=null){

            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&& mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
//                   printEdge(cur.left);
               }
            }
            System.out.print(cur.data+" ");
            cur=cur.right;

        }
//        printEdge(node);
        System.out.println();
    }

    public static void printEdge1(Node node){
        if(node==null){
            return;
        }
        Node tail=reverseEdge1(node);
        Node cur=tail;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.right;
        }
        reverseEdge1(tail);
    }

    public static Node reverseEdge1(Node node){
        if(node==null){
            return node;
        }
        Node prev=null;
        Node cur=node;
        while (cur!=null){
            Node next=cur.right;
            cur.right=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }

    public static void preTravel1(Node head){
        if(head==null){
            return;
        }
        Deque<Node> stack=new LinkedList<>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node temp=stack.pop();
            System.out.print(temp.data+" ");
            //reverse child
            if(temp.right!=null){
                stack.push(temp.right);
            }
            if(temp.left!=null){
                stack.push(temp.left);
            }
        }
        System.out.println();
    }

    public static void  postTravel1(Node head){
        if(head==null){
            return;
        }
        Deque<Node> stack=new LinkedList<>();
        Node prev=null;
        while (head!=null||!stack.isEmpty()){
            while (head!=null){
                stack.push(head);
                head=head.left;
            }
            head=stack.pop();
            if(head.right==null||head.right==prev){
                System.out.print(head.data+" ");
                prev=head;
                head=null;
            }else {
                stack.push(head);
                head=head.right;
            }
        }
        System.out.println();
    }

    public static void preTravel2(Node root){
        if(root==null){
            return;
        }
        Deque<Node> stack=new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node temp=stack.pollLast();
            System.out.print(temp.data+" ");
            if(temp.right!=null){
                stack.add(temp.right);
            }
            if(temp.left!=null){
                stack.add(temp.left);
            }
        }
    }

    public static void postTravel2(Node root){
       if(root==null){
           return ;
       }
       Deque<Node> stack=new LinkedList<>();
       Node prev=null;
       while (root!=null||!stack.isEmpty()){
           while (root!=null){
               stack.add(root);
               root=root.left;
           }
           root=stack.pollLast();
           if(root.right==null||root.right==prev){
               System.out.print(root.data+" ");
               prev=root;
               root=null;
           }else {
              stack.add(root);
              root=root.right;
           }
       }
    }

    public static void midTravel1(Node root){
        if(root==null){
            return;
        }
        Deque<Node> stack=new LinkedList<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.add(root);
                root=root.left;
            }
            root=stack.pollLast();
            System.out.print(root.data+" ");
            root=root.right;
        }
        System.out.println();
    }

    public static boolean isBalanced(Node node){

        return Math.abs(depth(node.right)-depth(node.left))<2;
    }


    public static int depth(Node node){
        if(node==null){
            return 0;
        }
        int lh=depth(node.left);
        int rh=depth(node.right);
        return Math.max(lh,rh)+1;
    }

    public static boolean isBalanced1(Node node){
        if(node==null){
            return true;
        }
        return helper(node)!=-1;
    }
    public static int helper(Node node){
        if(node==null){
            return 0;
        }
        int left=helper(node.left);
        if(left==-1){
            return -1;
        }
        int right=helper(node.right);
        if(right==-1){
            return -1;
        }
        if(Math.abs(left-right)>1){
            return -1;
        }
        return Math.max(left,right)+1;
    }





    public static void test1(){
        Node root=createBinaryTree();
        preTravel(root);
        System.out.println();
        midTravel(root);
        System.out.println();
        postTravel(root);
        System.out.println();
        levelTravel(root);
        levelTravel1(root);
        levelTravel2(root);
        findMostLeft(root);//d
        findMostRight(root);//c
        morrisIn(root);
        System.out.println();
    }
    public static void test2(){
        Node root=createBinaryTree();
        midTravel(root);
        System.out.println();
        morrisIn(root);
        System.out.println();
        preTravel(root);
        System.out.println();
        morrisPre(root);
        System.out.println();
        postTravel(root);
        System.out.println();
        System.out.println("-------");

        morrisPost1(root);
        morrisPost1(root);
        morrisPost(root);
        morrisPost1(root);
    }

    public static void test3(){
        Node root=createBinaryTree();
        morrisPost(root);
        morrisPost1(root);
    }
    public static void test4(){
        Node root=createBinaryTree();
        preTravel(root);
        System.out.println();
        preTravel1(root);
        preTravel2(root);
        System.out.println();
        postTravel(root);
        System.out.println();
        System.out.println("-");
        postTravel1(root);
        postTravel2(root);
        System.out.println();
        midTravel(root);
        System.out.println();
        midTravel1(root);
    }
    public static void test5(){
        Node root=createBinaryTree();
        System.out.println(depth(root));
        System.out.println(isBalanced(root));
        System.out.println(isBalanced1(root));
        Node r1=new Node("m1");
        Node r2=new Node("m2");
        root.left.left.left=r1;
        r1.left=r2;
        System.out.println(isBalanced(root));
        System.out.println(isBalanced1(root));
    }

    public static void main(String[] args) {
        test5();
    }
}
