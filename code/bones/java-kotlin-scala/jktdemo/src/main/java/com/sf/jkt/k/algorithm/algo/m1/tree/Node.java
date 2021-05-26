package com.sf.jkt.k.algorithm.algo.m1.tree;


import java.util.*;

/**
 * @Author WYMY
 * https://www.cnblogs.com/hu-yewen/p/5542591.html
 * 
 */
public class Node {
    int value;
    Node left;
    Node right;
    //如果节点重复count+1 没有重复count=0
    int count=0;
 
    public Node(int value) {
        this.value = value;
    }
    //节点添加 递归
    public void add1(Node node) {
        if (null == node) {
            return;
        }
       //新添加的节点值小于当前节点值
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add1(node);
            }
           //新添加的节点值小于当前节点值
        } else if(node.value>this.value){
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add1(node);
            }
        }else {
           //值相同的话，数量标记变量+1
            this.count++;
        }
    }
    //根据值查询节点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }
   //查询父节点方法1
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
 
   //查询父节点方法2 递归
    public Node searchP(int value) {
        if (this.value > value) {
            Node newNode = this.left;
            if (newNode == null) {
                return null;
            }
            if (newNode.value == value) {
                return this;
            } else {
                return newNode.searchP(value);
            }
        } else {
            Node newNode = this.right;
            if (newNode == null) {
                return null;
            }
            if (newNode.value == value) {
                return this;
            } else {
                return newNode.searchP(value);
            }
        }
    }


    public void add(Node node) {
        if (null == node) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else if(node.value>this.value){
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }else {
            this.count++;
        }
        //右旋转
        if(this.leftHigh()-this.rightHigh()>=2){
            if(this.left!=null&&this.left.leftHigh()-this.left.rightHigh()<0){
                //双旋转
                leftRotate(this.left);
                rightRotate(this);
            }else {
                //单旋转
                rightRotate(this);
            }
            //左旋转
        }else if(this.rightHigh()-this.leftHigh()>=2){
            if(this.right!=null&&this.right.leftHigh()-this.right.rightHigh()>0){
                //双旋转
                rightRotate(this.right);
                leftRotate(this);
            }else {
                //单旋转
                leftRotate(this);
            }
        }
    }






    //左旋转
    private void leftRotate(Node node) {
        //使新节点等于当前节点
        Node newNode=new Node(node.value);
        newNode.count=node.count;
        //将新节点的左节点设置为当前节点的左节点
        newNode.left=node.left;
        //将新节点的右节点设置为当前节点的右节点的左节点
        newNode.right=node.right.left;
        //使当前节点的右节点取代当前节点
        node.value=node.right.value;
        node.count=node.right.count;
        node.left=newNode;
        node.right=node.right.right;

        System.out.println("");
    }



    //右旋转
    private void rightRotate(Node node) {
        //使新节点等于当前节点
        Node newNode=new Node(node.value);
        newNode.count=node.count;
        //将新节点的右节点设置为当前节点的右节点
        newNode.right=node.right;
        //将新节点的左节点设置为当前节点的左节点的右节点
        newNode.left=node.left.right;

        node.value=node.left.value;
        node.count=node.left.count;
        node.left=node.left.left;
        node.right=newNode;
        System.out.println();
    }

    //返回左子树深度
    public int leftHigh(){
        if(this.left==null){
            return 0;
        }
        return this.left.high();
    }
    //返回右子树深度
    public int rightHigh(){
        if(this.right==null){
            return 0;
        }
        return this.right.high();
    }
    //返回树的深度
    public int high(){
        return Math.max(this.left==null?0:this.left.high(),this.right==null?0:this.right.high())+1;
    }

    public List<List<Integer>> levelTravel(){
       List<List<Integer>> ans=new LinkedList<>();
       Node root=this;
        Deque<Node> queue=new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> level=new ArrayList<>();
            for (int i=0;i<size;i++){
                Node node=queue.poll();
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


    /**
     *
     * 先以80左旋，再以90右旋转
     *            100
     *      80           120
     *  60      90
     *        85
     *
     * @return
     */
    public static Node createNode(){
        Node n1=new Node(100);
        Node n2=new Node(80);
        Node n3=new Node(120);
        Node n4=new Node(60);
        Node n5=new Node(90);
        Node n6=new Node(85);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n5.left=n6;
        return n1;
    }

    public static void test2(){
        Node n1=new Node(100);
        Node n2=new Node(80);
        Node n3=new Node(120);
        Node n4=new Node(60);
        Node n5=new Node(90);
        Node n6=new Node(85);
        n1.add(n2);
        n1.add(n3);
        n1.add(n4);
        n1.add(n5);
        n1.add(n6);
        System.out.println("");
    }

    public static void test1(){
        Node n1=createNode();
        n1.leftRotate(n1.left);
    }

    public static void main(String[] args) {
        test2();
    }

}
 