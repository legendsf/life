package com.sf.jkt.k.algorithm.algo.queue09;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mqueue {

    public static class DynamicQueue {
        Object[] items;
        int n = 0, head = 0, tail = 0, count = 0;

        public DynamicQueue(int capacity) {
            this.n = capacity;
            items = new Object[capacity];
        }

        public boolean enqueue(Object data) {
            if (tail == n) {
                if (head == 0) {//慢退出
                    return false;
                }
                //head 不等于0 则可以进行搬运
                for (int i = head; i < n; i++) {
                    items[i - head] = items[i];//往前搬运head个位置
                }
                head = 0;
                tail = tail - head;
            }
            items[tail] = data;
            tail++;
            return true;
        }

        public Object dequeue() {
            if (head == tail) return null;
            Object ret = items[head];
            head++;
            return ret;
        }

    }

    public static class ArrayQueue {
        String[] items;
        int n = 0;
        int head = 0;
        int tail = 0;

        public ArrayQueue(int capacity) {
            items = new String[capacity];
            n = capacity;
        }

        public boolean enqueue(String item) {
            if (tail == n) return false;
            items[tail] = item;
            tail++;
            return true;
        }

        public String dequeue() {
            if (head == n) {
                head = 0;
            }
            if (head == tail) return null;
            String ret = items[head];
            head++;
            return ret;
        }
    }

    public static class CircleQueue {
        Object[] items;
        int n = 0, head = 0, tail = 0;

        public CircleQueue(int capacity) {
            this.n = capacity;
            items = new Object[capacity];
        }

        public boolean equeue(Object item) {
            if ((tail + 1) % n == tail) return false;//队列满
            items[tail] = item;
            tail = (tail + 1) % n;
            return true;
        }

        public Object dequeue() {
            if (head == tail) return null;
            Object ret = items[head];
            head = (head + 1) % n;
            return ret;
        }
    }

    public static class MabQueue<E> {
        Object[] items;
        int count = 0, putIndex = 0, takeIndex = 0;
        ReentrantLock lock;
        Condition notFull;
        Condition notEmpty;

        public MabQueue(int capacity) {
            items = new Object[capacity];
            lock = new ReentrantLock();
            notFull = lock.newCondition();
            notEmpty = lock.newCondition();
        }

        public void enqueue(E e) {
            items[putIndex] = e;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        }

        public E dequeue() {
            E x = (E) items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return x;
        }

        public void put(E e) throws InterruptedException {
            checkNotNull(e);
            lock.lockInterruptibly();
            try {
                while (count == items.length) {
                    notFull.await();
                }
                enqueue(e);
            } finally {
                lock.unlock();
            }
        }

        public E take() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                while (count == 0) {
                    notEmpty.await();
                }
                return dequeue();
            } finally {
                lock.unlock();
            }
        }

        public void checkNotNull(E e) {
            if (e == null) {
                throw new NullPointerException();
            }
        }

    }

    public static class LinkQueue<E> {
        Node tail;
        Node head;

        public static class Node {
            Object data;
            Node next;

            public Node(Object data) {
                this(data, null);
            }

            public Node(Object data, Node node) {
                this.data = data;
                this.next = node;
            }

        }

        public LinkQueue() {
            head = tail = new Node(null);
        }

        public void enqueue(E e) {
            tail = tail.next = new Node(e);
        }

        public E dequeue() {
            Node h = head;
            Node first = head.next;
            head.next = h;//null;
            E x = (E) first.data;
            first.data = null;
            return x;
        }

    }

    public static void main(String[] args) throws Exception {
//        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
//        queue.put(1);
//        queue.put(2);
//        queue.put(3);
        LinkedBlockingQueue q1 = new LinkedBlockingQueue(2);
        q1.put(1);
        q1.put(2);
        q1.take();
        System.out.println("end");
    }
}
