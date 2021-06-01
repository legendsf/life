package com.sf.jkt.k.algorithm.algo.m1.algo.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MLruCache2 {
    //LinkedHashMap,map存放值，访问过则删除再add到末尾，过量则删除第一个元素

    private static class LinkedHashMapCache {
        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LinkedHashMapCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>(capacity);
        }

        int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Integer value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        }

        void put(int key, int value) {
            if (get(key) != -1) {
                map.put(key, value);
                return;
            }
            map.put(key, value);
            if (map.size() > capacity) {
                map.remove(map.entrySet().iterator().next().getKey());
            }
        }
    }

    //Map+双向链表，map存放当前Node，判断是否存在，访问过替换尾部，过量则删除头部
    private static class DoublyListNodeMapCache {
        int capacity;
        DoublyListNode head;
        DoublyListNode tail;
        Map<Integer, DoublyListNode> map;

        public DoublyListNodeMapCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            head = new DoublyListNode(-1, -1);
            head.next = tail;
            tail = new DoublyListNode(-1, -1);
            tail.prev = head;
        }

        int get(int key) {
            if (map.containsKey(key)) {
                //移动到尾部
                DoublyListNode node = map.get(key);
                moveToTail(node);
                return node.value;
            }
            return -1;
        }

        void put(int key, int value) {
            if (get(key) != -1) {
                map.get(key).value = value;
                return;
            }
            DoublyListNode node = new DoublyListNode(key, value);
            map.put(key, node);
            moveToTail(node);
            if (map.size() > capacity) {
                DoublyListNode hnext = head.next;
                map.remove(hnext.key);
                hnext.prev.next = hnext.next;
                hnext.prev = null;
                hnext.next = null;
            }
        }

        private void moveToTail(DoublyListNode node) {
            if (node.next == tail) {
                return;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }
    }

    private static class DoublyListNode {
        int key, value;
        DoublyListNode prev;
        DoublyListNode next;

        public DoublyListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            String pr = prev == null ? "null" : "" + prev.key;
            String ne = next == null ? "null" : "" + next.key;
            return "DoublyListNode{" +
                    "key=" + key +
                    ", value=" + value +
                    ", prev=" + pr +
                    ", next=" + ne +
                    '}';
        }
    }

    //Map+单链表,map存放prevNode，判断是否存在，访问过替换尾部，过量则删除头部
    private static class ListNodeCache {
        int capacity;
        Map<Integer, ListNode> map;
        ListNode head;
        ListNode tail;

        public ListNodeCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            head = new ListNode(-1, -1);
            tail = head;
        }

        int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            ListNode prev = map.get(key);
            int value = prev.next.value;
            moveTotail(prev);
            return value;
        }

        void put(int key, int value) {
            if (get(key) != -1) {
                map.get(key).next.value = value;
                return;
            }
            ListNode node = new ListNode(key, value);
            map.put(node.key, tail);
            tail.next = node;
            tail = node;
            if (map.size() > capacity) {
                map.remove(head.next.key);
                map.remove(head.next.next.key);
                head.next = head.next.next;
                map.put(head.next.key, head);
            }
        }

        private void moveTotail(ListNode prev) {
            if (prev == null || prev.next == tail) {
                return;
            }
            ListNode cur = prev.next;
            prev.next = cur.next;
            map.put(cur.next.key, prev);
            tail.next = cur;
            map.put(cur.key, tail);
            tail = cur;
        }
    }

    private static class ListNode {
        int key, value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            String nk = next == null ? "null" : "" + next.key;
            return "ListNode{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + nk +
                    '}';
        }
    }

    private static class ListNodeCache1 {
        int capacity;
        Map<Integer, ListNode> map;
        ListNode head;
        ListNode tail;

        public ListNodeCache1(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            head = new ListNode(-1, -1);
            tail = head;
        }

        int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            ListNode prev = map.get(key);
            Integer value = prev.next.value;
            ListNode cur = prev.next;
            prev.next = cur.next;
            map.put(cur.next.key, prev);
            tail.next = cur;
            map.put(cur.key, tail);
            tail = cur;
            cur.next = null;
            return value;
        }

        void put(int key, int value) {
            if (get(key) != -1) {
                map.get(key).value = value;
                return;
            }
            ListNode node = new ListNode(key, value);
            map.put(node.key, tail);
            tail.next = node;
            tail = node;
            if (map.size() > capacity) {
                map.remove(head.next.key);
                map.remove(head.next.next.key);
                head.next = head.next.next;
                map.put(head.next.key, head);
            }
        }
    }

    public static void test1() {
        LinkedHashMapCache cache1 = new LinkedHashMapCache(3);
        cache1.put(1, 1);
        cache1.put(2, 2);
        cache1.put(3, 3);
        cache1.put(4, 4);
        System.out.println(cache1.map);
        System.out.println(cache1.get(1));
        System.out.println(cache1.get(4));
        System.out.println(cache1.get(3));
        System.out.println(cache1.get(2));
        System.out.println(cache1.map);
        System.out.println("------");
        LinkedHashMapCache cache4 = new LinkedHashMapCache(3);
        cache4.put(1, 1);
        cache4.put(2, 2);
        cache4.put(3, 3);
        cache4.put(4, 4);
        System.out.println(cache4.map);
        System.out.println(cache4.get(1));
        System.out.println(cache4.get(4));
        System.out.println(cache4.get(3));
        System.out.println(cache4.get(2));
        System.out.println(cache4.map);
        System.out.println("------");
        DoublyListNodeMapCache cache3 = new DoublyListNodeMapCache(3);
        cache3.put(1, 1);
        cache3.put(2, 2);
        cache3.put(3, 3);
        cache3.put(4, 4);
        System.out.println(cache3.map);
        System.out.println(cache3.get(1));
        System.out.println(cache3.get(4));
        System.out.println(cache3.get(3));
        System.out.println(cache3.get(2));
        System.out.println(cache3.map);
    }

    public static void main(String[] args) {
        test1();
    }
}
