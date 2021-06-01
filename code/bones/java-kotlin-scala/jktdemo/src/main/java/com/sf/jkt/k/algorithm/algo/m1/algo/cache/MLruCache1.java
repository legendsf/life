package com.sf.jkt.k.algorithm.algo.m1.algo.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MLruCache1 {
    public static class Cache1{
        int capacity;
        Map<Integer,Integer> map;

        public Cache1(int capacity) {
            this.capacity = capacity;
            map=new LinkedHashMap<>(capacity);
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            Integer value=map.remove(key);
            map.put(key,value);
            return  value;
        }
        public void put(int key,int value){
            if(get(key)!=-1){
                map.put(key,value);
                return;
            }
            map.put(key,value);
            if(map.size()>capacity){
                map.remove(map.entrySet().iterator().next().getKey());
            }
        }
    }

    public static class Cache2{ //
        int capacity;
        Map<Integer, DllNode>map;
        DllNode head;
        DllNode tail;

        public Cache2(int capacity) {
            this.capacity = capacity;
            map=new HashMap<>(capacity);
            head=new DllNode(-1,-1);
            tail=new DllNode(-1,-1);
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            DllNode cur=map.get(key);
            if(cur.next!=tail){
               //delete
                cur.prev.next=cur.next;
                cur.next.prev=cur.prev;
               //movetotail
                tail.prev.next=cur;
                cur.prev=tail.prev;
                cur.next=tail;
                tail.prev=cur;
            }
            return cur.value;
        }

        public void put(int key,int value){
            if(get(key)!=-1){
                map.get(key).value=value;
                return;
            }
            DllNode temp=new DllNode(key,value);
            map.put(key,temp);
            tail.prev.next=temp;
            temp.prev=tail.prev;
            temp.next=tail;
            tail.prev=temp;
            if(map.size()>capacity){
                //del
                map.remove(head.next.key);
                head.next=head.next.next;
                head.next.prev=head;
            }
        }
    }

    public static class Cache3{
        int capacity;
        Map<Integer,ListNode>map;
        ListNode head;
        ListNode tail;

        public Cache3(int capacity) {
            this.capacity = capacity;
            map=new HashMap<>(capacity);
            head=new ListNode(-1,-1);
            tail=head;
        }

        public void put(int key,int value){
            if(get(key)!=-1){
                map.get(key).next.value=value;
                return;
            }
            ListNode cur=new ListNode(key,value);
            map.put(key,tail);
            tail.next=cur;
            tail=tail.next;
            if(map.size()>capacity){
                map.remove(head.next.key);
                head.next=head.next.next;
            }

        }
        public int get(int key){
            if(!map.containsKey(key)){
                return -1;
            }
            ListNode prev=map.get(key);
            ListNode cur=prev.next;
            if(cur!=tail){
                map.put(key,tail);
                map.put(cur.next.key,prev);
                prev.next=cur.next;
                tail.next=cur;
                tail=tail.next;
            }
            return cur.value;
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

    public static class DllNode{
        int key;
        int value;
        DllNode prev;
        DllNode next;

        public DllNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
