package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_04;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Code_07_IsBSTAndCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 搜索二叉树
     *
     * @param head
     * @return
     */
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    /**
     * 完全二叉树
     *
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static boolean isFBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return true;
    }


    public static boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(Node node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.value <= lower || node.value >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.value) && isValidBST(node.right, node.value, upper);
    }

    public static boolean isValidBST1(Node node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.value <= lower || node.value >= upper) {
            return false;
        }
        return isValidBST1(node.left, lower, node.value) && isValidBST1(node.right, node.value, upper);
    }
    public static boolean isValidBST1(Node node,long[]pre){
        if (node==null){
            return true;
        }
        boolean l=isValidBST1(node.left,pre);
        if (node.value<=pre[0]){
            return false;
        }
        pre[0]=node.value;
        boolean r=isValidBST1(node.right,pre);
        return  l&&r;
    }

    public static boolean isValidBST1(Node node) {
        if (node == null) {
            return true;
        }
        return isValidBST1(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST3(Node root) {
        Deque<Node> stack = new LinkedList<Node>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.value <= inorder) {
                return false;
            }
            inorder = root.value;
            root = root.right;
        }
        return true;
    }

    public static boolean isValidBST4(Node head){
        if (head==null){
            return true;
        }
        Deque<Node> stack=new LinkedList<Node>();
        Double inorder=-Double.MAX_VALUE;
        Node cur=head;
        while (!stack.isEmpty()||cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();
            if (cur.value<=inorder){
                return false;
            }
            inorder=(double) cur.value;
            cur=cur.right;
        }
        return true;
    }

    public static boolean isBST1(Node head){
        if (head==null){
            return true;
        }
        boolean res=true;
        Node pre=null,cur1=head,cur2=null;
        while (cur1!=null){
            cur2=cur1.left;
            if (cur2!=null){
               while (cur2.right!=null&&cur2.right!=cur1){
                   cur2=cur2.right;
               }
               if (cur2==null){
                  cur2.right=cur1;
                  cur1=cur1.left;
                  continue;
               }else {
                   cur2.right=null;
               }
            }
            if (pre!=null&&pre.value>cur1.value){
                return false;
            }
            pre=cur1;
            cur1=cur1.right;
        }
        return res;
    }

    public static boolean isCBT1(Node head){
        if (head==null){
            return true;
        }
        Queue<Node> queue=new LinkedList<>();
        boolean leaf=false;
        Node l=null,r=null;
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            l=head.left;
            r=head.right;
            if ((leaf)&(l!=null||r!=null)||(l==null&&r!=null)){
                return false;
            }
            if (l!=null){
                queue.offer(head.left);
            }
            if (r!=null){
                queue.offer(head.right);
            }
//            else {
//                leaf=true;
//            }
            if (l==null||r==null){
                leaf=true;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
		head.right.right= new Node(2);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isBST1(head));
        System.out.println(isValidBST1(head));
        System.out.println(isValidBST1(head,new long[]{Long.MIN_VALUE}));
        System.out.println(isValidBST4(head));
        System.out.println(isCBT(head));
        System.out.println(isCBT1(head));
        System.out.println(isFBT(head));

    }
}