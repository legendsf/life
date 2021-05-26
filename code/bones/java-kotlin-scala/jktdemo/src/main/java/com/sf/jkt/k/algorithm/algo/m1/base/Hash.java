package com.sf.jkt.k.algorithm.algo.m1.base;

/***
 * hash
 * https://www.it610.com/article/2464781.htm
 *
 */
public class Hash {

    public static int FNVHash2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * Bernstein's hash
     *
     * @param key   输入字节数组
     * @param level 初始hash常量
     * @return 结果hash
     */
    public static int bernstein1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        } ;
        return hash;
    }


    /**
     * MASK值，随便找一个值，最好是质数
     */
    static int M_MASK = 0x8765fed1;
    /**
     * 一次一个hash
     * @param key 输入字符串
     * @return 输出hash值
     */
    public static int oneByOneHash3(String key)
    {
        int   hash, i;
        for (hash=0, i=0; i<key.length(); ++i)
        {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
//   return (hash & M_MASK);
        return hash;
    }


    public static void testHashcode() {
        System.out.println(new Hash().hashCode());
    }

    public static void main(String[] args) {
        testHashcode();

    }

    public int hash(Object key) {
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }


}
