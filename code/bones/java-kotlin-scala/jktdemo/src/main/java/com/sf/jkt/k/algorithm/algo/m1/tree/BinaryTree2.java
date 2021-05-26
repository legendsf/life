package com.sf.jkt.k.algorithm.algo.m1.tree;

import com.sf.jkt.k.algorithm.algo.m1.base.ListNode;

public class BinaryTree2 {
    public static class Node{
        String data;
        Node left;
        Node right;

        public Node(String data) {
            this.data = data;
        }
    }
    public static void morrisPos(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }
    public static void printEdge(Node node){
        Node tail =reverseEdge(node);
        Node cur = tail;
        while (cur != null ){
            System.out.print(cur.data+" ");
            cur =cur.right;
        }
        reverseEdge(tail);
    }
    public static Node reverseEdge(Node node){
        Node pre = null;
        Node next = null;
        while (node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
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


    public ListNode sortList(ListNode head){
        if(head==null||head.next==null){
            return head;
        }
        ListNode slow=head,fast=head.next;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode tmp=slow.next;
        slow.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(tmp);
        ListNode h=new ListNode(0);
        ListNode res=h;

        while (left!=null&&right!=null){
            if(left.val-right.val<0){
                h.next=left;
                left=left.next;
            }else {
                h.next=right;
                right=right.next;
            }
            h=h.next;
        }
        h.next=left!=null?left:right;
        return res.next;
    }

    public static ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    public static ListNode sortList3(ListNode head){
        if(head==null){
            return head;
        }
        int length=0;
        ListNode node=head;
        while (node!=null){
            length++;
            node=node.next;
        }
        ListNode dummyHead=new ListNode(0,head);
        for(int subLen=1;subLen<length;subLen<<=1){
            ListNode prev=dummyHead,cur=dummyHead.next;
            while (cur!=null){
               ListNode head1=cur;
               for(int i=1;i<subLen&&cur.next!=null;i++){
                    cur=cur.next;
               }
               ListNode head2=cur.next;
               cur.next=null;
               cur=head2;
               for(int i=1;i<subLen&&cur!=null&&cur.next!=null;i++){
                   cur=cur.next;
               }
               ListNode next=null;
               if(cur!=null){
                   next=cur.next;
                   cur.next=null;
               }
               ListNode merged=merge3(head1,head2);
               prev.next=merged;
               while (prev.next!=null){
                   prev=prev.next;
               }
               cur=next;
            }
        }
        return dummyHead.next;
    }

    public static ListNode merge3(ListNode head1,ListNode head2){
        ListNode dummyHead= new ListNode(0);
        ListNode temp=dummyHead,temp1=head1,temp2=head2;
        while (temp1!=null && temp2!=null){
            if(temp1.val<=temp2.val){
                temp.next=temp1;
                temp1=temp1.next;
            }else {
                temp.next=temp2;
                temp2=temp2.next;
            }
            temp=temp.next;
            temp.next=temp1!=null?temp1:temp2;
        }
        return dummyHead.next;
    }



    public static ListNode sortList1(ListNode head){
        if(head==null){
            return head;
        }
        return null;
    }

    public static void test1(){
       Node head=createBinaryTree();
        morrisPos(head);
    }
    public static ListNode createNode(){
        ListNode n1=new ListNode(4);
        ListNode n2=new ListNode(2);
        ListNode n3=new ListNode(1);
        ListNode n4=new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        print(n1);
        System.out.println();
        return n1;
    }
    public static void test2(){
        ListNode node=createNode();
       ListNode res=  sortList2(node);
       print(res);
        System.out.println();
    }

    public static void print(ListNode node){
        if(node==null){
            return;
        }
        System.out.print(node.val+" ");
        print(node.next);
    }

    public static void main(String[] args) {
       test2();
    }
}
