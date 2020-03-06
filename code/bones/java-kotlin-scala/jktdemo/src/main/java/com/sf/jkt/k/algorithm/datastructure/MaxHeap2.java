package com.sf.jkt.k.algorithm.datastructure;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxHeap2<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap2(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap2() {
        data = new ArrayList<>();
    }


    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void swap(int i, int j) {
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    public void add(E e) {
        data.add(e);//放到末尾
        //保持堆的性质，堆化
        siftUp(data.size() - 1);
    }

    public E findMax() {
        return data.get(0);
    }

    /**
     * 取出堆顶元素
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        swap(0, data.size() - 1);
        siftDown(0);
        return ret;
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {//如果子节点大于父节点，则交换
            swap(k, parent(k));//交换数据
            k = parent(k);//循环终止条件，进行下一轮父节点判断
        }
    }

    private void siftDown2(int k) {
        while (leftChild(k) < data.size()) {//左子存在
            int j = leftChild(k);
            if (j + 1 < data.size() && //右子存在
                    data.get(j).compareTo(data.get(j + 1)) < 0) {//并且右大于左
                j = rightChild(k);
            }
            //如果父节点大于子节点则终止，说明保持了堆的性质
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            //否则交换父节点，子节点
            swap(k, j);
            k = j;//继续下推子节点，直到保持堆得性质
        }
    }

    private void siftDown(int k) {
        //leftChild存在
        while (leftChild(k) < data.size()) {
            int j = leftChild(k);
            //rightChild存在,且值大于leftChild的值
            if (j + 1 < data.size() &&
                    data.get(j).compareTo(data.get(j + 1)) < 0)
                j = rightChild(k);
            //data[j]是leftChild和rightChild中最大的

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            swap(k, j);
            k = j;
        }

    }


    /**
     * 取出堆中最大的元素，替换为元素
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public MaxHeap2(E[] arrs) {
        data = new ArrayList<>(Arrays.asList(arrs));
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public MaxHeap2(E[] arrs, boolean flag) {
        data = new ArrayList<>(Arrays.asList(arrs));
        for (int i = 0; i < data.size(); i++) {
            siftUp(i);
        }
    }


    public static void main(String[] args) {
//        Integer[] arr = new Integer[]{3, 2, 1, 6, 4, 5};
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6};
//        MaxHeap2<Integer> maxHeap=new MaxHeap2<>(arr,true);
        MaxHeap2<Integer> maxHeap = new MaxHeap2<>(arr);
        maxHeap.data.stream().forEach(System.out::println);
    }
}
