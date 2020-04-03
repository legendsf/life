package com.sf.jkt.k.algorithm.algo;

public class StrTest {
    public static void main(String[] args) {
        char[] str = new char[]{'a', 'b', 'c'};
        str = new char[]{'1', '2', '3'};
//       permutation(str,0);
//        permutation(str);
        combination1(str);
    }

    public static void permutation(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                permutation(str, i + 1);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }

    /**
     * 数组元素的全组合
     */
    static void combination(char[] chars) {
        char[] subchars = new char[chars.length]; //存储子组合数据的数组
        //全组合问题就是所有元素(记为n)中选1个元素的组合, 加上选2个元素的组合...加上选n个元素的组合的和
        for (int i = 0; i < chars.length; ++i) {
            final int m = i + 1;
            combination(chars, chars.length, m, subchars, m);
        }
    }

    /**
     * n个元素选m个元素的组合问题的实现. 原理如下:
     * 从后往前选取, 选定位置i后, 再在前i-1个里面选取m-1个.
     * 如: 1, 2, 3, 4, 5 中选取3个元素.
     * 1) 选取5后, 再在前4个里面选取2个, 而前4个里面选取2个又是一个子问题, 递归即可;
     * 2) 如果不包含5, 直接选定4, 那么再在前3个里面选取2个, 而前三个里面选取2个又是一个子问题, 递归即可;
     * 3) 如果也不包含4, 直接选取3, 那么再在前2个里面选取2个, 刚好只有两个.
     * 纵向看, 1与2与3刚好是一个for循环, 初值为5, 终值为m.
     * 横向看, 该问题为一个前i-1个中选m-1的递归.
     */
    static void combination(char[] chars, int n, int m, char[] subchars, int subn) {
        if (m == 0) { //出口
            for (int i = 0; i < subn; ++i) {
                System.out.print(subchars[i]);
            }
            System.out.println();
        } else {
            for (int i = n; i >= m; --i) { // 从后往前依次选定一个
                subchars[m - 1] = chars[i - 1]; // 选定一个后
                combination(chars, i - 1, m - 1, subchars, subn); // 从前i-1个里面选取m-1个进行递归
            }
        }
    }

    /**
     * 数组元素的全排列
     */
    static void permutation(char[] chars) {
        permutation(chars, 0, chars.length - 1);
    }

    /**
     * 数组中从索引begin到索引end之间的子数组参与到全排列
     */
    static void permutation(char[] chars, int begin, int end) {
        if (begin == end) { //只剩最后一个字符时为出口
            System.out.println(String.valueOf(chars));

        } else {
            for (int i = begin; i <= end; ++i) { //每个字符依次固定到数组或子数组的第一个
                if (canSwap(chars, begin, i)) { //去重
                    swap(chars, begin, i); //交换
                    permutation(chars, begin + 1, end); //递归求子数组的全排列
                    swap(chars, begin, i); //还原
                }
            }
        }
    }

    static void swap(char[] chars, int from, int to) {
        char temp = chars[from];
        chars[from] = chars[to];
        chars[to] = temp;
    }

    static boolean canSwap(char[] chars, int begin, int end) {
        for (int i = begin; i < end; ++i) {
            if (chars[i] == chars[end]) {
                return false;
            }
        }
        return true;
    }

    public static void permutation1(char[] chars) {
        permutation(chars, 0, chars.length - 1);
    }

    public static void permutation1(char[] chars, int begin, int end) {
        if (begin == end) {
            //只剩最后一个字符串，就是全排列出口
            System.out.println(String.valueOf(chars));
        } else {
            for (int i = begin; i <= end; i++) {//固定首字母
                if (canSwap1(chars, begin, i)) {
                    swap1(chars, begin, i);//交换
                    permutation(chars, begin + 1, end);//子符串全排列，递归到只剩下最后一个字符
                    swap1(chars, begin, i);//还原
                }
            }
        }
    }

    public static boolean canSwap1(char[] chars, int begin, int end) {
        for (int i = begin; i < end; i++) {
            if (chars[i] == chars[end]) {
                return false;
            }
        }
        return true;
    }

    public static void swap1(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void combination1(char[] chars) {
        char[] subchar = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int m = i + 1;
            combination(chars, chars.length, m, subchar, m);
        }
    }

    public static void combination1(char[] chars, int n, int m, char[] subchar, int subn) {
        if (m == 0) {
            for (int i = 0; i < subn; i++) {
                System.out.println(subchar[i]);
            }
        } else {
            for (int i = n; i >= m; i--) {//从后往前依次选定一个
                subchar[m - 1] = subchar[i - 1];
                combination(chars, n - 1, m - 1, subchar, subn);
            }
        }
    }

}
