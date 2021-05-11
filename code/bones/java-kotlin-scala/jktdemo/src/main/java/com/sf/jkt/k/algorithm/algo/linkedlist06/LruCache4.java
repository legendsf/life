package com.sf.jkt.k.algorithm.algo.linkedlist06;

import java.util.HashMap;
import java.util.Map;

public class LruCache4 {
    int capacity;
    Map<Integer, ListNode> map = new HashMap<>();
    ListNode head = new ListNode(-1, -1);//dummy
    ListNode tail;

    public LruCache4(int capacity) {
        this.capacity = capacity;
        tail=head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode prev = map.get(key);
        ListNode cur = prev.next;
        if (cur != tail) {//不等于tail时要移动到末尾
            map.put(cur.next.key, prev);
            map.put(cur.key, tail);
            prev.next=cur.next;
            tail.next=cur;
            tail=tail.next;
            cur.next=null;
        }
        return cur.value;
    }

    public void put(int key,int value){
        if(map.containsKey(key)){
           get(key);//重新覆盖也相当于一次访问，所以要移动
            map.get(key).next.value=value;//set newvalue
            return;
        }
        ListNode node=new ListNode(key,value);
        map.put(node.key,tail);
        tail.next=node;
        tail=tail.next;
        if(map.size()>capacity){
            //remove from head;
            map.remove(head.next.key);
            head.next=head.next.next;
            map.put(head.next.key,head);
        }
    }

    public static class ListNode {
        int key;
        int value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void testlru(){
        LruCache4 lru1=new LruCache4(5);
        lru1.put(1,1);
        lru1.put(2,2);
        lru1.put(3,3);
        lru1.put(4,4);
        lru1.put(5,5);
        lru1.put(6,6);
        for(int i=1;i<=5;i++){
            System.out.println(lru1.get(i));
        }
        System.out.println("-------");
        lru1.get(2);
        lru1.put(7,7);
        for(int i=1;i<=7;i++){
            System.out.println(lru1.get(i));
        }
    }

    public static void main(String[] args) {
        testlru();
    }
}
