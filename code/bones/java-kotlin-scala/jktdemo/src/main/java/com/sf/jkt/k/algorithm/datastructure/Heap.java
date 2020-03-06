package com.sf.jkt.k.algorithm.datastructure;

/**
 * Created by XuTao on 2018/11/5 22:10
 * ···最小堆···
 * 《注意： 实际上并不需要用节点来真正构造一颗树，我们只是在数组中操作排序，调整好的数组就是一个堆的层遍历结果》
 *
 * 插入：
 *     也是插入末尾，然后调整，调整也应该是一个连续向上的过程，建树就是一个连续插入的过程
 *
 * 删除最小：
 *     即删除root：
 *     用末尾一个代替root，删除末尾，然后siftDown，如果子节点有更小的，每次只需要找到最小的子节点，然后交换即可。
 *
 * siftDown:
 *     如果建树得以保证，那么如果子节点有更小的，每次只需要找到最小的子节点，然后交换即可。
 *     如果是一个乱的树，那么就要考虑，比较麻烦， 解决方式：
 *     i 从 最后一个节点的父节点出开始迭代，直到i = 0;
 *     每次检查时，将大的节点交换到末尾——就是要到底，如果大，就要成为叶节点，不是只交换一次（用循环）
 *
 * 那么就有两种构建方法：
 *     1.乱序构建，调整（ 一个一个添加（从数组中），添加到最后一个，树的最右最下方的那个，然后siftUp,从下往上调整就可以了 ，O（log2（n）））
 *     2.一次一节点，依次调整
 *
 * <p>
 * 思考题： 设计算法检查一个完全二叉树是不是堆，是的话是最大堆还是最小堆。
 * 思路：元素1个，同为最大最小堆
 * 元素>1个：
 * 判断第一二个大小
 * 第一个大： 可能为最大堆，然后递归校验，如果每一个节点都比子节点大，那么是最大堆，否则不是堆
 * 第二个大： 可能为最小堆，然后递归校验，如果每一个节点都比子节点小，那么是最小堆，否则不是堆
 * <p>
 *
 *时间复杂度分析：
 * 建树：两种方式都是 O(nlog2(n))
 * 插入： O(log2(n))
 * 删除： O(log2(n))
 */


public class Heap {
    private int[] data;
    private final int maxSize = 128;  //预设大小，足够就行
    private int heapSize; //实际大小

    public Heap(int[] input) {
        data = new int[maxSize];
        heapSize = input.length;
        for (int i = 0; i < heapSize; i++) {//这个地方其实并不好，只是将传入的数组读入我的数组中，一方有不断插入操作，如果没有插入操作则不必要；
            data[i] = input[i];
        }
    }


    public void build_1() {
        /**
         * 建树方法1：
         *      每次插入一个节点
         */
        int a = heapSize;
        heapSize = 0;
        for (int i = 0; i < a; i++) {
            insert(data[i]);
        }
    }

    public void build_2() {
        /**
         * 建树方法2：
         *      以原来的乱序进行调整：siftDown
         */
        if (heapSize <= 1) return;
        for (int i = getParent(heapSize - 1); i >= 0; i--) {  // 从末元素的父节点开始，一次一次进行siftDown
            siftDown(i);
        }
    }

    /**
     * 由上而下调整， sift——筛
     * @param start
     */
    public void siftDown(int start) {
        //start至少1子，不用担心溢出问题
        while (getLeft(start) < heapSize) {  //注意，这里必须是小于，不能等于，如果该节点的左节点是末尾节点则结束，条件是getLeft(start)==heapSize-1
            int min = 0;//判别有没有发生交换的条件
            //无右子
            if (getRight(start) >= heapSize) {
                if (data[start] > data[getLeft(start)]) {
                    min = getLeft(start);
                    swap(start, min);
                }
            }
            //2子
            else {
                min = data[getLeft(start)] > data[getRight(start)] ? getRight(start) : getLeft(start);
                if (data[start] > data[min]) {
                    swap(start, min);
                }
            }
            if (min == 0) break;//满足堆条件，退出
            start = min; //不满足堆条件，还可以调整，继续循环
        }
    }

    /**
     * 由下而上调整
     * @param start 开始的下标
     */
    public void siftUp(int start) {
        if (start <= 0) return;
        while (data[start] < data[getParent(start)]) {  //一直发生交换，直到满足条件
            swap(start, getParent(start));
            start = getParent(start);
            if (start <= 0) break;// root
        }
    }

    public void insert(int a) {
        /**
         * 插入的话会使数组长度加一，比较麻烦，于是我建立一个比较大的树，用一个较大的量maxSize来限定堆的最大容量，用heapSize来声明实际的容量
         */
        data[heapSize] = a;
        siftUp(heapSize);
        heapSize++;
    }

    public int getLeft(int i) {
        return 2 * i + 1;
    }

    public int getRight(int i) {
        return 2 * i + 2;
    }

    public int getParent(int i) {
        if (i == 0) return -1;
        return (i - 1) >> 1;  //除以2
    }

    public void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void display() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(data[i] + "  ");
        }
        System.out.println();
    }



    public static void main(String[] args) {
        int[] a = new int[]{3,2,1,4,5};
        Heap heap = new Heap(a);
        heap.build_2();
        heap.display();

    }
}
