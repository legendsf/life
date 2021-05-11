package com.sf.jkt.k.algorithm.algo.linkedlist06;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Lru {
   public  static class Lru1{
       int capacity;
       Map<Integer,Integer> cache;

       public Lru1(int capacity) {
           this.capacity = capacity;
           this.cache =new LinkedHashMap<>();
       }

       public int get(int key){
           if(!cache.containsKey(key)){
               return -1;
           }
           int value=cache.get(key);
           cache.remove(key);
           cache.put(key,value);
           return value;
       }

       public void put(int key,int value){
          if(cache.containsKey(key)){
              cache.remove(key);
              cache.put(key,value);
              return;
          }
          cache.put(key,value);
          if(cache.size()>capacity){
              int rmk=cache.entrySet().iterator().next().getKey();
              cache.remove(rmk);//删除下一个
          }
       }

   }
    public static class ListNode{
       int key;
        int value;
        ListNode prev;
        ListNode next;

        public ListNode(int key,int value) {
            this.key=key;
            this.value = value;
        }
    }
    /**
     * 思路：双向链表+map
     *  链表是为了解决把被访问的元素移到链表末尾
     *  超过capacity 则删除 链表头
     *  map<key,node>是为了判断存不存在某个key，不存在则加入链表，存在则覆盖并移动链表
     */
   public static class Lru2{
        int capacity;
        Map<Integer,ListNode> map=new HashMap<>();
        ListNode head=new ListNode(-1,-1);
        ListNode tail=new ListNode(-1,-1);
        {
            head.next=tail;
            tail.prev=head;
        }

        public Lru2(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            ListNode node=map.get(key);
            delNode(node);
            moveTotail(tail,node);
            return node.value;
        }

        public void put(int key,int value){
            if(get(key)!=-1){//存在，直接移动了一遍
                //存在覆盖旧值
                map.get(key).value=value;
            }
            ListNode node = new ListNode(key,value);
            map.put(key,node);
            moveTotail(tail,node);
            if(map.size()>capacity){
                map.remove(head.next.key);
                delNode(head.next);
            }
        }
    }

    public static void delNode(ListNode node){
       if(node==null){
           return;
       }
       node.prev.next=node.next;
       node.next.prev=node.prev;
       node.prev=null;
       node.next=null;
    }

    public static void moveTotail(ListNode tail,ListNode node){
       tail.prev.next=node;
       node.prev=tail.prev;
       node.next=tail;
       tail.prev=node;
    }

    public static void testLru2(){
        Lru2 lru1=new Lru2(5);
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

    public static void testLru1(){
       Lru1 lru1=new Lru1(5);
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
        testLru1();
        System.out.println("*******");
        testLru2();
    }
}
