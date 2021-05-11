package com.sf.jkt.k.algorithm.algo.linkedlist06;

import java.util.HashMap;
import java.util.Map;

public class LruCache3 {
    int capacity;
    ListNode head=new ListNode(-1,-1);//dummy
    ListNode tail=head;//
    Map<Integer,ListNode> map= new HashMap<>();

    public LruCache3(int capacity) {
        this.capacity = capacity;
        head.next=tail;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        /**
         * 如果涉及到结点的移动必须要设置两个参数
         * 因为要保存中间的临时结点，因为前后关系一改变，再用后一个结点就容易出错。
         */
        ListNode prev=map.get(key);
        ListNode cur=prev.next;
        if(cur!=tail){//不是末尾要移动末尾
           prev.next=cur.next;
           map.put(cur.next.key,prev);
           map.put(cur.key,tail);
           cur.next=null;
           tail.next=cur;
           tail=tail.next;
        }
        return cur.value;
    }

    public void put(int key,int value){
        if(map.containsKey(key)){
            get(key);//移动到尾部结点,因为最新访问的必须在尾部，put包含了一次访问
            map.get(key).next.value=value;//赋值
        }
        ListNode node=new ListNode(key,value);
        //放新的
        map.put(node.key,tail);
        tail.next=node;
        tail=tail.next;
        //删除老的
        if(map.size()>capacity){
            map.remove(head.next.key);
            head.next=head.next.next;
            map.put(head.next.key,head);
        }
    }

    public static class ListNode{
        int key;
        int value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void testlru(){
        LruCache3 lru1=new LruCache3(5);
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
