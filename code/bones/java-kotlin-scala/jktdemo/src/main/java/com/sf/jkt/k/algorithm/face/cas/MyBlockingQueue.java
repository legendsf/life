package com.sf.jkt.k.algorithm.face.cas;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
    private Object[] elements;
    private int head = 0, tail = 0;
    private int size;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public MyBlockingQueue(int capacity) {
        this.elements = new Object[capacity];
    }

    public void put(E e) {
        lock.lock();
        try {
            while (size == elements.length) {
                notFull.await();
            }
            elements[tail] = e;
            if (++tail == elements.length) {
                tail = 0;
            }
            size++;
            notEmpty.signal();
        } catch (InterruptedException ex) {

        } finally {
            lock.unlock();
        }
    }

    public void take(E e) {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            e = (E) elements[head];
            elements[head] = null;
            if (++head == elements.length) {
                head = 0;
            }
            size--;
            notFull.signal();
        } catch (InterruptedException ex) {

        }
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
