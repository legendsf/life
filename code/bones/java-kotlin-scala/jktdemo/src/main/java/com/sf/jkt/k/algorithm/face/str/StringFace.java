package com.sf.jkt.k.algorithm.face.str;

import java.util.*;

public class StringFace {

    public static void main(String[] args) {
//        testFrequence("abbccc");
//        isOdd(17);
//        isEven(17);
//        System.out.println(findOddOnce1(new int[]{4, 1, 1, 3, 3}));
        int i = findFirstBitIsOne(8);
        System.out.println(i);
        System.out.println(Integer.toBinaryString(8));
        System.out.println(isBit1(8, 3));
        int[] oddTwice = findOddTwice(new int[]{4, 4, 4, 1, 1, 2, 2, 3, 3, 3});
        Arrays.stream(oddTwice).forEach(System.out::println);
    }





    //找出数组中奇数的数

    /***
     * 只有一个数出现奇数次
     * 偶数异或后为0,剩下的是奇数
     * @param arr
     * @return
     */
    public static int findOddOnce(int[] arr) {
        int num = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            num ^= arr[i];
        }
        return num;
    }

    public static int findOddOnce1(int[] arr) {
        int s = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            s ^= arr[i];
        }
        return s;
    }

    /***
     * 数组中出现奇数个数为两个怎么找到,其他元素为偶数个数
     * @param arr
     */
    public static int[] findOddTwice(int[] arr) {
        //找到异或结果，必然是两个奇数的异或结果
        int s = arrXOR(arr);
        //找到第一个位是1的位置
        int index1 = findFirstBitIsOne(s);
        //遍历数组，把N位为1的数分成两个数组，两个奇数一个在其中一部分，一个在另一部分
        //此时问题转化为，从数组中找到唯一的一个奇数
        int len = arr.length;
        int n1 = 0, n2 = 0;
        for (int i = 0; i < len; i++) {
            if (isBit1(arr[i], index1)) {
                n1 ^= arr[i];
            } else {
                n2 ^= arr[i];
            }
        }
        return new int[]{n1, n2};
    }

    /**
     * 判断一个数第N位是1返回true，否则返回false
     *
     * @param num
     * @param index
     * @return
     */
    public static boolean isBit1(int num, int index) {
        num = num >> index;
        if ((num & 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * 从后往前找第一位是1的,从0开始
     * 1000，返回3
     * @param num
     * @return
     */
    public static int findFirstBitIsOne(int num) {
        int indexBit = 0;
        //从后往前找第一个为1的位数，如果是0继续循环，循环到1或者都为0；
        while (((num & 1) == 0) && (indexBit < 32)) {
            num >>= 1;//右移，进行下一次循环
            indexBit++;
        }
        return indexBit;
    }

    public static int arrXOR(int[] arr) {
        int s = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            s ^= arr[i];
        }
        return s;
    }

    public static void outputOdd(int[] arr) {
        int[] tmp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

        }
    }

    public static void isEven(Integer i) {
        if ((i & 1) == 1) {
            System.out.println("是奇数");
        } else {
            System.out.println("是偶数");
        }
    }

    public static void isOdd(Integer i) {
        if ((i & 1) == 0) {
            System.out.println("是偶数");
        } else {
            System.out.println("是奇数");
        }
    }

    public static void testFrequence(String str) {
        TreeMap<Character, Integer> treeMap = new TreeMap<>(
        );
        for (int i = 0; i < str.length(); i++) {
            Character tmp = str.charAt(i);
            if (treeMap.get(tmp) == null) {
                treeMap.put(tmp, 1);
            } else {
                treeMap.put(tmp, treeMap.get(tmp) + 1);
            }
        }

        ByValueComparator comparator = new ByValueComparator(treeMap);
        List<Character> l1 = new ArrayList<Character>(treeMap.keySet());
        Collections.sort(l1, comparator);
        StringBuilder sbd = new StringBuilder();
        for (Character tmp : l1) {
            System.out.println(tmp + "---" + treeMap.get(tmp));
            int times = treeMap.get(tmp);
            for (int i = 0; i < times; i++) {
                sbd.append(tmp);
            }
        }
        System.out.println(sbd.toString());

    }

    public static class ByValueComparator implements Comparator<Character> {
        TreeMap<Character, Integer> tm = null;

        public ByValueComparator(TreeMap<Character, Integer> tm) {
            this.tm = tm;
        }

        @Override
        public int compare(Character o1, Character o2) {
            if (!tm.containsKey(o1) || !tm.containsKey(o2)) {
                return 0;
            }
            if (tm.get(o1) < tm.get(o2)) {
                return 1;
            } else if (tm.get(o1) == tm.get(o2)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
