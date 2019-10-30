package com.sf.jkt.k.algorithm.face.cas.sort;

public class QuickSort {
    private static int count;

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] num = {3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        num = new int[]{5, 9, 2, 8, 3, 7};
        num = new int[]{5,3,6,2,8,7};
        num=new int[]{3,2};
        System.out.println(arrayToString(num, "未排序"));
        QuickSort(num, 0, num.length - 1);
        System.out.println(arrayToString(num, "排序"));
        System.out.println("数组个数：" + num.length);
        System.out.println("循环次数：" + count);

    }

    /**
     * 快速排序
     *
     * @param num   排序的数组
     * @param left  数组的前针
     * @param right 数组后针
     */
    private static void QuickSort(int[] num, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = num[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (num[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (num[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        count++;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }

    /**
     * 将一个int类型数组转化为字符串
     *
     * @param arr
     * @param flag
     * @return
     */
    private static String arrayToString(int[] arr, String flag) {
        String str = "数组为(" + flag + ")：";
        for (int a : arr) {
            str += a + "\t";
        }
        return str;
    }

}
