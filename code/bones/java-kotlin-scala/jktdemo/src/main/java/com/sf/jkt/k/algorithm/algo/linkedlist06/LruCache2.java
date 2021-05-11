package com.sf.jkt.k.algorithm.algo.linkedlist06;


import java.util.HashMap;
import java.util.Map;

public class LruCache2 {
    int capacity;
    Map<Integer,ListNode> map=new HashMap<>();//node.key,node.prev
    ListNode head=new ListNode(-1,-1);//dummy
    ListNode tail=head;

    public LruCache2(int capacity) {
        this.capacity = capacity;
        head.next=tail;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode prev=map.get(key);
        //删除和设新值
        ListNode cur=prev.next;
        if(cur!=tail){
            prev.next=prev.next.next;
            map.put(prev.next.key,prev);
            //移动和设新值
            tail.next=cur;
            map.put(cur.key,tail);
            tail=tail.next;
        }
        //返回
        return cur.value;
    }

    public void put(int key,int value){
        //已经做了一次移动
        if(get(key)!=-1){
            //设置新值
           map.get(key).next.value=value;
           return;
        }
        ListNode node=new ListNode(key,value);
        //movetotail setmap
        map.put(key,tail);
        tail.next=node;
        tail=tail.next;
        //reomvehead.next setmap
        if(map.size()>capacity){
            map.remove(head.next.key);//删除
            map.put(head.next.next.key, head);
            head.next=head.next.next;
        }
    }

    public static class ListNode{
        int key;
        int value;
        ListNode next;

        public ListNode(int key,int value) {
            this.key = key;
            this.value=value;
        }
    }

    public static void testlru(){
        LruCache2 lru1=new LruCache2(5);
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
