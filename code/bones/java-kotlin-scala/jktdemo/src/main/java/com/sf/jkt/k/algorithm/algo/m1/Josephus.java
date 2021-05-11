package com.sf.jkt.k.algorithm.algo.m1;

public class Josephus {

    public static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node createCircle(int n){
        Node head=new Node(-1);
        Node cur=head;
        for(int i=1;i<=n;i++){
            Node temp=new Node(i);
            cur.next=temp;
            cur=cur.next;
        }
        cur.next=head.next;
        return head;
    }

    public static  int josephus1(int n,int m){
        if(n==1||m<2){
            return n;
        }
        Node head=createCircle(n);
        Node cur=head.next;
        while (cur!=cur.next){
            for(int i=1;i<m-1;i++){
                cur=cur.next;
            }
            System.out.print(cur.next.value+"-->");
            cur.next=cur.next.next;
            cur=cur.next;
        }
        System.out.println();
        return cur.value;
    }


    public static int josephus(int n,int m){
        return n==1?n:(josephus(n-1,m)+m-1)%n+1;
    }

    public static void test1(){
        System.out.println(josephus(10, 3));
        System.out.println(josephus(9, 3));
        System.out.println(josephus(8, 3));
        System.out.println(josephus(7, 3));
        System.out.println(josephus(6, 3));
        System.out.println(josephus(5, 3));
        System.out.println(josephus(4, 3));
        System.out.println(josephus(3, 3));
    }
    public static void test2(){
        System.out.println(josephus(10,3));
        System.out.println(josephus(10,1));
        System.out.println(josephus(1,1));
        System.out.println(josephus1(10,3));
        System.out.println(josephus1(10,1));
        System.out.println(josephus1(1,1));
    }
    public static void main(String[] args) {
        test2();
    }
}
