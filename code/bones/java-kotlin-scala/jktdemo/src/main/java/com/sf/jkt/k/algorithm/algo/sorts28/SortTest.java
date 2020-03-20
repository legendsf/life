package com.sf.jkt.k.algorithm.algo.sorts28;

import java.util.Arrays;

public class SortTest {
    static int arr[] = {7, 5, 3, 2, 3, 5, 4};

    public static void main(String[] args) {

//        System.out.println(3 / 2);
//        System.out.println(3 % 2);

//        Arrays.stream(mergeSort(arr)).forEach(System.out::println);
//        quick_sort1(arr, 0, arr.length - 1);
        heapSort1(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }

            swap(arr, i, index);
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序原理
     * https://baijiahao.baidu.com/s?id=1644158198885715432&wfr=spider&for=pc
     */
    public static void hellSort() {
        int arr[] = {7, 5, 3, 2, 4};

        //希尔排序（插入排序变种版）
        for (int i = arr.length / 2; i > 0; i /= 2) {
            //i层循环控制步长
            for (int j = i; j < arr.length; j++) {
                //j控制无序端的起始位置
                for (int k = j; k > 0 && k - i >= 0; k -= i) {
                    if (arr[k] < arr[k - i]) {
                        int temp = arr[k - i];
                        arr[k - i] = arr[k];
                        arr[k] = temp;
                    } else {
                        break;
                    }
                }
            }
            //j,k为插入排序，不过步长为i
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void hellSort2() {
        int arr[] = {7, 5, 3, 2, 4};
        //gap
        for (int i = arr.length / 2; i > 0; i = i / 2) {
            //交换元素的起始位置
            for (int j = i; j < arr.length; j++) {
                //pre>=0,end>0
                for (int k = j; k - i >= 0 && k > 0; k = k - i) {
                    if (arr[k] < arr[k - i]) {
                        //如果前大于后，则交换前后
                        swap(arr, k, k - i);
                    }
                }
            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    //快速排序
    static void quick_sort(int s[], int l, int r) {
        if (l < r) {

            //Swap(s[l], s[(l + r) / 2]);
            //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                // 从右向左找第一个小于x的数
                while (i < j && s[j] >= x) {
                    j--;
                }
                if (i < j) {
                    s[i++] = s[j];
                }
                // 从左向右找第一个大于等于x的数
                while (i < j && s[i] <= x) {
                    i++;
                }
                if (i < j) {
                    s[j--] = s[i];
                }
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }


    static void quick_sort1(int[] arr, int l, int r) {
        //递归终止条件
        if (l < r) {
            //从后往前找第一个小于等于基准值的数目，交换之，把小的换到前面
            int i = l, j = r, x = arr[l];
            //基准值位置终止
            while (i < j) {
                //如果后面大就一直往前找，
                while (i < j && arr[j] >= x) {
                    j--;
                }
                if (i < j) {
                    //把后面的换到前面，并且递增条件
                    arr[i++] = arr[j];
                }
                //从前往后找第一个大于X的
                while (i < j && arr[i] <= x) {
                    i++;
                }
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            arr[i] = x;
            //已经找到一个基准值的位置了
            quick_sort(arr, l, i - 1);
            quick_sort(arr, i + 1, r);
            //从前往后找第一个大于基准值的数，交换，把大的换到后面
        }
    }


    private static void heapSort(int[] arr) {
        //1.建堆
        buildMaxHeap(arr);
        //2.将堆顶元素与最后一个元素交换 调整堆
        int len = arr.length - 1;
        while (len > 0) {
            int temp = arr[0];
            arr[0] = arr[len];
            arr[len] = temp;
            adjustHeap(arr, 0, --len);
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void buildMaxHeap(int[] arr) {
        //1.建堆从最后一个非叶子结点开始
        int begin = arr.length / 2 - 1;
        for (int i = begin; i >= 0; i--) {
            //运用向下调整的方式建堆
            adjustHeap(arr, i, arr.length - 1);
        }
    }

    private static void adjustHeap(int[] arr, int begin, int end) {
        int temp = 0;
        int child = 0;
        for (temp = arr[begin]; 2 * begin + 1 <= end; begin = child) {
            child = 2 * begin + 1;
            //找出左右孩子中较大的值
            if (child + 1 <= end && arr[child + 1] > arr[child]) {
                child++;
            }
            //如果父节点 小于左右孩子中较大的那一个  则交换
            if (temp < arr[child]) {
                arr[begin] = arr[child];
            } else {
                break;
            }
        }
        //最大值
        arr[begin] = temp;
    }


    public static void heapSort1(int[] arr) {
        //建最大堆
        buildMaxHeap1(arr);
        //堆顶和最后一个交换，大的始终在后面
        int len = arr.length - 1;
        while (len > 0) {
            int temp = arr[0];
            arr[0] = arr[len];
            arr[len] = temp;
            adjustHeap1(arr, 0, --len);
        }
    }

    public static void buildMaxHeap1(int[] arr) {
        //从非叶子结点开始构建堆
        int begin = arr.length / 2 - 1;
        for (int i = begin; i >= 0; i--) {
            adjustHeap1(arr, i, arr.length - 1);
        }
    }

    public static void adjustHeap1(int[] arr, int begin, int end) {
        //temp 默认是max,叶子结点不超过数组长度
        int temp = 0;
        int child = 0;
        for (temp = arr[begin]; 2 * begin + 1 <= end; begin = child) {
            child = 2 * begin + 1;
            //如果右子树大于左子树，则child 变为右子树，否则左子树即为最大值
            if (child + 1 <= end && arr[child + 1] > arr[child]) {
                child++;
            }
            if (temp < arr[child]) {
                arr[begin] = arr[child];
            } else {
                break;
            }
        }
        //父亲结点为最大值
        arr[begin] = temp;
    }

}


