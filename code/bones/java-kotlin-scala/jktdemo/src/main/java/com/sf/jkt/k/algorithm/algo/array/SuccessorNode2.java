package com.sf.jkt.k.algorithm.algo.array;

public class SuccessorNode2 {
    public static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 左子树的最右边
     * 没有左子树，则往上面（父亲）找
     *    直到找到parent==null || parent.right=node
     *    parent=null,说明，一直找到根结点的父亲，也找不到前驱，前驱=null
     *    parent.right=node,说明，从中序(左根右）遍历来看肯定能找到一个前驱,就是parent
     * @param node
     * @return
     */
    public static Node getPoineerNode(Node node){
        if (node == null) {
            return null;
        }
        if(node.left!=null){
            return getMostRight(node.left);
        }else {
            //没有左子树，那么前驱只有可能是父亲结点之上，并且自己一定在某个右子树上，否则，前驱为null
            Node parent=node.parent;
            //parent==null 停止， parent.right=node 停止
            while (parent!=null&&parent.right!=node){
                 node=parent;
                 parent=node.parent;
            }
            return parent;
        }
    }
    
    public static Node getSuccessorNode(Node node){
        if(node==null){
            return node;
        }
        if(node.right!=null){
            //如果有右子树，后继结点在右边子树，最左边
            return getMostLeft(node.right);
        }else {
            Node parent=node.parent;
            //没有右子树，同时有是后继结点，说明，肯定在某个祖先结点上的某一个右子树里面
            while (parent!=null&&parent.left!=node){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }
    
    public static Node getMostRight(Node node){
        while (node!=null&&node.right!=null){
            node=node.right;
        }
        return node;
    }
    public static Node getMostLeft(Node node){
        while (node!=null &&node.left!=null){
            node=node.left;
        }
        return node;
    }

    /***
     *        1
     *      2
     *     3
     *    4  6
     *   5     7
     */
    public static Node buildTree() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left=n2;
        n2.left=n3;
        n3.left=n4;
        n3.right=n6;
        n4.left=n5;
        n6.right=n7;

        n7.parent=n6;
        n5.parent=n4;
        n6.parent=n3;
        n3.parent=n2;
        n2.parent=n1;

        System.out.println(getPoineerNode(n5));
        System.out.println(getSuccessorNode(n5));
        return n1;
    }

    public static void main(String[] args) {
       buildTree();
    }
}
