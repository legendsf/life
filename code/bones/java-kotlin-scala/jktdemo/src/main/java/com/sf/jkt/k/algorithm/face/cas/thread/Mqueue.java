package com.sf.jkt.k.algorithm.face.cas.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Mqueue<E> {
    private Object[] arr;
    private int tail, head, size;
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public Mqueue(int capacity) {
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
