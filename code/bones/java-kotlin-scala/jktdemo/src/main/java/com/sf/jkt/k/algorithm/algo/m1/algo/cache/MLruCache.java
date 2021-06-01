package com.sf.jkt.k.algorithm.algo.m1.algo.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MLruCache {
    public static class Cache1{
        int capacity;
        Map<Integer,Integer> map;

        public Cache1(int capacity) {
            this.capacity = capacity;
            map=new LinkedHashMap<>(capacity);
        }

        public void put(int key,int value){
            if(map.containsKey(key)){
                map.remove(key);//删除原有位置
                map.put(key,value);//放到末尾
                return;
            }
            map.put(key,value);
           if(map.size()>capacity){
               map.remove(map.entrySet().iterator().next().getKey());//删除第一个
           }
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            Integer value=map.get(key);
            map.remove(key);
            map.put(key,value);
            return value ;
        }
    }

    public static class Cache2{
       int capacity;
       Map<Integer,DNode> map;
       DNode head;
       DNode tail;

        public Cache2(int capacity) {
            this.capacity = capacity;
            map=new HashMap<>(capacity);
            head=new DNode(-1,-1);
            tail=new DNode(-1,-1);
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            DNode cur=map.get(key);
            if(cur.next!=tail){
                //删除
                cur.prev.next=cur.next;
                cur.next.prev=cur.prev;
                //移动到tail之前
                cur.prev=tail.prev;
                cur.prev.next=cur;
                cur.next=tail;
                tail.prev=cur;
            }
            return cur.value;
        }

        public void put(int key,int value){
            if(get(key)!=-1){
                map.get(key).value=value;
            }
            DNode temp=new DNode(key,value);
            map.put(key,temp);
            temp.prev=tail.prev;
            temp.prev.next=temp;
            temp.next=tail;
            tail.prev=temp;
            if(map.size()>capacity){
                map.remove(head.next.key);
                head.next=head.next.next;
                head.next.prev=head;
            }
        }
    }

    public static class Cache3{
        int capacity;
        Map<Integer,Node> map;
        Node head;//dummy
        Node tail;//

        public Cache3(int capacity) {
            this.capacity = capacity;
            map=new HashMap<>(capacity);
            head=new Node(-1,-1);
            tail=head;
        }

        public void put(int key,int value){
            if(get(key)!=-1){
                map.get(key).next.value=value;
                return;
            }
            Node temp=new Node(key,value);
            map.put(key,tail);
            tail.next=temp;
            tail=tail.next;
            if(map.size()>capacity){
                map.remove(head.next.key);
                head.next=head.next.next;
                map.put(head.next.key,head);
            }
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            Node pre=map.get(key);
            Node cur=pre.next;
            if(cur!=tail){
                pre.next=cur.next;
                map.put(cur.next.key,pre);
                map.put(cur.key,tail);
                tail.next=cur;
                tail=tail.next;
                cur.next=null;
            }
            return cur.value;
        }

    }

    public static class Node{
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public static class DNode{
        int key;
        int value;
        DNode prev;
        DNode next;

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void testCache3(){
        Cache3 cache=new Cache3(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(3,3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }


    public static void main(String[] args) {
        testCache3();
    }
}
