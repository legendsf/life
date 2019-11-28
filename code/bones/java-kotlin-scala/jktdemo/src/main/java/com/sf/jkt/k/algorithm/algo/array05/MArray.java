package com.sf.jkt.k.algorithm.algo.array05;

public class MArray<T> {
    Object[] data;
    int size;

    public MArray(int capacity) {
        this.data = new Object[capacity];
//        this.data= new T[capacity]{};
        size = 0;
    }

    public MArray() {
        this(10);
    }

    public int capacity() {
        return data.length;
    }

    public int count() {
        return this.count();
    }

    public boolean isEmpty() {
        return this.data == null;
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index:" + index + ";size:" + size);
        }
    }

    public void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("index:" + index + ";size:" + size);
        }
    }

    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public void add(int index, T e) {
        checkIndexForAdd(index);
        if (size == data.length) {
            resize(2 * data.length);
        }
//        System.arraycopy(data, index, data, index + 1, size - index);
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public void addLast(T e) {
        add(size, e);
    }

    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return oldValue;
    }

    public void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = (T) data[i];
        }
        data = newData;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public void remove(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }

    }


    public static void main(String[] args) {
        MArray<Integer> arr = new MArray<>(10);
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(4);
        arr.remove(2);
        for (int i = 0; i < arr.size; i++) {
            System.out.println(arr.data[i]);
        }
    }

}
