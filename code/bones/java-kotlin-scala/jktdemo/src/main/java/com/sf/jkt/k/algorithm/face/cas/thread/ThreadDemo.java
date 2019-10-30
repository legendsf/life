package com.sf.jkt.k.algorithm.face.cas.thread;

import java.lang.Object;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
//        testCondition();
//        testMq();
        testStorage();
    }

    public synchronized void output() {
        int count = 10;
        while (count > 0) {
            try {
                count--;
                System.out.println(count);
                this.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void testMq() throws Exception {
        Mqueue<String> queue = new Mqueue<String>(3);
        queue.put("a");
        queue.put("b");
        queue.put("c");
//        queue.put("x");
        queue.take();
        queue.put("d");
        System.out.println(queue.take());

    }

    public static void testStorage() throws InterruptedException {
        Storage<String> st = new Storage<>(3);
        st.produce("a");
        st.produce("b");
        st.produce("c");
//        st.produce("e");
        st.consume();
        st.produce("d");
        System.out.println(st.consume());
    }

    static ReentrantLock lock = new ReentrantLock(false);
    static Condition cond1 = lock.newCondition();
    static Condition cond2 = lock.newCondition();

    public static void testCondition() throws Exception {
        lock.lockInterruptibly();
        cond1.signal();
        cond2.signal();
        cond1.await();
        lock.unlock();
    }

}


class Storage<E> {
    E[] arr;
    int head, tail, size;
    private java.lang.Object putLock=new Object();
    private java.lang.Object getLock=new Object();

    public Storage(int capacity) {
        arr = (E[]) new Object[capacity];
    }

    public void produce(E e) throws InterruptedException {
        synchronized (putLock) {
            while (size == arr.length) {
                putLock.wait();
            }
            arr[tail] = e;
            size++;
            if (++tail == arr.length) {
                tail = 0;
            }
            synchronized (getLock) {
                getLock.notify();
            }
        }
    }

    public E consume() throws InterruptedException {
        E e = null;
        synchronized (putLock) {
            synchronized (getLock) {
                while (size == 0) {
                    getLock.wait();
                }
                e = arr[head];
                size--;
                if (++head == arr.length) {
                    head = 0;
                }
            }
            putLock.notify();
        }
        return e;
    }
}


class ProducerAndConsumer<E> {
    //lock storage
    LinkedBlockingDeque<E> storage;

    public ProducerAndConsumer(LinkedBlockingDeque<E> storage) {
        this.storage = storage;
    }

    public void produce(E e) {
        storage.add(e);
    }

    public E consume() {
        return storage.removeFirst();
    }
}


class Mqueue3<E> {
    private java.lang.Object[] arr;
    private int tail, head, size;
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public Mqueue3(int capacity) {
        arr = new Object[capacity];
        lock = new ReentrantLock(false);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();

    }


    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (size == arr.length) {//while防止虚假唤醒
                //没有空间，不能继续放，等待的线程消费后唤醒
                notFull.await();
            }
            arr[tail] = e;
            size++;
            if (++tail == arr.length) {
                //到末尾则重置到开头，可以形成循环放,
                tail = 0;
            }
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        E e = null;
        try {
            while (size == 0) {
                notEmpty.await();
            }
            e = (E) arr[head];
            size--;
            if (++head == arr.length) {
                head = 0;
            }
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return e;
    }

    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}


/***
 * ABCABCABC
 ***/

class PrintThread extends Thread {
    private int count;
    private String msg;
    private Object preLock;
    private Object selfLock;

    public PrintThread(String msg, Object preLock, Object selfLock, int count) {
        this.msg = msg;
        this.preLock = preLock;
        this.selfLock = selfLock;
        this.count = count;
    }

    public static void testABC() throws Exception {
        int count = 10;
        Object locka = new Object();
        Object lockb = new Object();
        Object lockc = new Object();
        Thread a = new PrintThread("a", lockc, locka, count);
        Thread b = new PrintThread("b", locka, lockb, count);
        Thread c = new PrintThread("c", lockb, lockc, count);
        a.start();
        TimeUnit.MILLISECONDS.sleep(100);
        b.start();
        TimeUnit.MILLISECONDS.sleep(100);
        c.start();
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void run() {
        while (count > 0) {
            synchronized (preLock) {
                synchronized (selfLock) {
                    System.out.print(msg);
                    count--;
                    selfLock.notifyAll();
                }
                if (count == 0) {
                    preLock.notifyAll();
                } else {
                    try {
                        preLock.wait();
                    } catch (InterruptedException ex) {
                        //go to next loop
                    }
                }
            }
        }
        //可以使用cylicbarrier 等待所有线程执行到这里结束
    }
}