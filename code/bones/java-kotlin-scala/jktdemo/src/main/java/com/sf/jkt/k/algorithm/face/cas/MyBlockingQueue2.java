package com.sf.jkt.k.algorithm.face.cas;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue2<E> {
    private Object[] elements;
    private int head = 0, tail = 0, size = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public MyBlockingQueue2(int capacity) {
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

    public E take(E e) {
        E result = null;
        lock.lock();
        try {
            while (tail == elements.length) {
                notEmpty.await();
            }
            result = (E) elements[head];
            elements[head] = null;
            if (++tail == elements.length) {
                tail = 0;
            }
            size--;
            notFull.signal();
        } catch (InterruptedException ex) {
        } finally {
            lock.unlock();
        }
        return result;
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
