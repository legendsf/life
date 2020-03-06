package com.sf.jkt.k.algorithm.datastructure;


import java.util.Arrays;
import java.util.Random;

/**
 * 最大堆 | 采用动态数组结构
 * 从索引为0的地方开始存储，动态数组
 * 最大堆元素必须可以比较，即实现Comparable接口
 * 父结点: （n - 1）/ 2
 * 左孩子: 2 * n + 1
 * 右孩子: 2 * n + 2
 *
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> {


    /**
     * 底层数据结构 | 动态数组
     */
    private Array<T> array;

    public MaxHeap() {
        this.array = new Array<>();
    }

    /**
     * 将数组堆化，heapify过程
     *
     * @param array
     */
    public MaxHeap(T[] array) {
        this.array = new Array<>(array);
        //heapify
        heapify(array);
    }

    /**
     * heapify过程 | 最大堆
     * 1. i 初始化为 第一个非叶子结点的索引，通过最后一个元素的父结点的方式确定
     * 2. i之后每次减1，就是上一个非叶子结点，直到根结点也完成下沉化
     * 3. 最后完成堆化
     *
     * @param array
     */
    public void heapify(T[] array) {

        for (int i = parent(array.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    /**
     * 返回堆的元素个数
     *
     * @return
     */
    public int size() {
        return array.getSize();
    }

    /**
     * 堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 获取某个结点的父结点索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new RuntimeException("根结点没有父结点");
        }

        return (index - 1) / 2;
    }

    /**
     * 获取某个结点的左孩子索引
     *
     * @param index
     * @return
     */
    private int lchild(int index) {
        return (2 * index) + 1;
    }

    /**
     * 获取某个结点的右孩子索引
     *
     * @param index
     * @return
     */
    private int rchild(int index) {
        return (2 * index) + 2;
    }

    /**
     * 给堆添加一个元素 | 时间复杂度O(logn)
     *
     * @param data
     */
    public void add(T data) {
        //动态数组中追加元素
        array.addLast(data);
        //新添元素执行上浮操作,传入新添元素的索引，即最后一个位置
        siftUp(array.getSize() - 1);

    }

    /**
     * index索引的元素执行上浮操作
     *
     * @param index
     */
    private void siftUp(int index) {

        //index不可以是根节点，所以必须>0 ，且上浮结点的值必须大于其父节点的值，只要满足条件，一直上浮
        while (index > 0 && array.get(index).compareTo(array.get(parent(index))) > 0) {
            //交换位置
            array.swap(index, parent(index));
            //下一个
            index = parent(index);
        }

    }

    /**
     * 取出堆中的最大值 | 时间复杂度O(logn)
     *
     * @return
     */
    public T extractMax() {

        //找到最大值
        T max = array.get(0);
        //最后元素和根结点交换位置
        array.swap(0, array.getSize() - 1);
        //删除最后的元素
        array.removeLast();
        //下沉操作
        siftDown(0);
        return max;

    }

    /**
     * 对索引为index的元素进行下沉操作
     *
     * @param index
     */
    private void siftDown(int index) {

        /**
         * 下沉同样是一个循环，只要不是叶子结点不断循环
         * 1. 只要下沉元素的左孩子的索引小于等于数组的最大索引，就代表下沉元素还不是叶子结点，还可以循环
         */
        while (lchild(index) <= array.getSize() - 1) {

            /**
             * 1. 求左右孩子谁的值大，就取谁的索引
             */
            //获得左右孩子索引
            int lIndex = lchild(index);
            int rIndex = rchild(index);
            int max = 0;

            //求最大
            //如果其右孩子索引大于数组的最大索引，则越界，不存在右孩子 | while循环已经保证了肯定有左孩子 |右孩子索引没有越界，就代表有右孩子
            if (rIndex > array.getSize() - 1) {
                max = lIndex;
                //如果有右孩子，则比较左右孩子的大小，取最大的孩子的索引
            } else {
                max = array.get(lIndex).compareTo(array.get(rIndex)) > 0 ? lIndex : rIndex;
            }

            /**
             * 下沉元素与最大孩子结点比较
             * 1. 如果下沉元素比最大的孩子结点都要大，那么这就代表下沉已经结束，堆结构特性已经满足
             */
            if (array.get(index).compareTo(array.get(max)) >= 0) {
                break;
            }

            //如果下沉元素没有最大孩子结点大，则交换位置，继续下沉
            array.swap(index, max);
            //下一个
            index = max;


        }
    }

    /**
     * 取出最大元素，同时插入一个新元素
     * 原思想，extractMax  +  add 两个O(logn)操作
     * 但是，我们直接把要插入的元素替换到根结点位置，再下沉，就只需要一个O(logn)了
     *
     * @param data
     * @return
     */
    public T replace(T data) {
        //获得最大值
        T max = array.get(0);
        //根结点替换为新元素
        array.set(0, data);
        //对新元素进行下沉
        siftDown(0);
        return max;
    }


    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(3);
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(4);
        maxHeap.add(5);

        int len=5;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = maxHeap.extractMax();
        }

        System.out.println(Arrays.toString(arr));
    }

}
