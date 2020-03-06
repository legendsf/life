package com.sf.jkt.k.algorithm.datastructure;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {  //  user assign size
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10); // default size
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }


    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is Illegal!");
        }
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is Illegal ! ");
        }
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addLast(E e) { //末尾添加
        add(size, e);
    }

    public void addFirst(E e) { //头部添加
        add(0, e);
    }


    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(int index, E e) {
        rangeCheck(index);
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }


    public E remove(int index) {  // remove data[index] and return the value
        rangeCheck(index);
        E res = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;//loitering objects  != memory  leak
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2); //防止复杂度的震荡
        }
        return res;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    public void removeElement(E e) { //only remove one(may repetition) and user not know whether is deleted.
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // new method
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array : size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
