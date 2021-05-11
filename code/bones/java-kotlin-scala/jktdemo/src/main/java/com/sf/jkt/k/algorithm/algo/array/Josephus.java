package com.sf.jkt.k.algorithm.algo.array;

public class Josephus {

    public static int josephus(int n, int m) {
        return n == 1 ? n : (josephus(n - 1, m) + m - 1) % n + 1;
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int josephus1(int n, int m) {
        if (n == 1 || m <= 1) {
            return n;
        }
        Node head = createNodeCircle(n);
        Node cur = head.next;
        int count = 1;
        while (cur != cur.next) {
            if (count < m - 1) {
                count++;
                cur = cur.next;
            } else {
                count = 1;
                System.out.print(cur.next.data + "-->");
                cur.next = cur.next.next;
                cur = cur.next;
            }
        }
        System.out.println("left:" + cur.data);
        return cur.data;
    }

    public static int josephus2(int n, int m) {
        if (n == 1 || m < 2) {
            return n;
        }
        Node head = createNodeCircle(n);

        Node cur = head.next;
        int count = 1;
        while (cur != cur.next) {
            if (count < m - 1) {
                count++;
                cur = cur.next;
            } else {
                count = 1;
                System.out.print(cur.next.data + "-->");
                cur.next = cur.next.next;
                cur = cur.next;
            }
        }
        System.out.println("left:" + cur.data);
        return cur.data;
    }

    public static Node createNodeCircle(int n) {
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 1; i <= n; i++) {
            Node temp = new Node(i);
            cur.next = temp;
            cur = cur.next;
        }
        cur.next = head.next;
        return head;
    }

    public static int josephus4(int n,int m) {
        if(n==1||m<2){
            return n;
        }
        Node head=createNOdeCircle1( n);
        Node cur=head.next;
        while (cur!=cur.next){
            for(int i=1;i<m-1;i++){
                cur=cur.next;//
            }
            System.out.print(cur.next.data+"-->");
            cur.next=cur.next.next;
            cur=cur.next;
        }
        System.out.println("left:"+cur.data);
       return cur.data;
    }

    public static Node createNOdeCircle1(int n){
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

    public static int josephus3(int n,int m){
        return n==1?n:(josephus3(n-1,m)+m-1)%n +1;
    }

    public static void main(String[] args) {
        System.out.println(josephus(10, 2));
        System.out.println(josephus1(10, 2));
        System.out.println(josephus2(10, 2));
        System.out.println("---");
        System.out.println(josephus3(10, 2));
        System.out.println("---");
        System.out.println(josephus4(10, 2));
    }
}
