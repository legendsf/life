package com.sf.jkt.k.algorithm.algo.m1.algo;

public class BitDemo {

    public static int getMaxInt(){
        return (1<<31)-1;
    }
    public static int getMaxInt1(){
        return  ~(1<<31);
    }
    static long getMaxLong(){
        return ((long)1 << 127) - 1;//9223372036854775807
    }
    public static boolean isOdd(int n){
        return (n&1)==1;
    }
    public static boolean isPowofTwo(int n){
        return n>0?(n&(n-1))==0:false;
    }
    public static int quyu(int m,int n){
        return  m&(n-1);
    }

    /**
     * 从低位到高位,取n的第m位
     * @param
     */
    public static int getBit(int n,int m){
        return (n>>(m-1)) &1;
    }

    //从低位到高位.将n的第m位置1
    public static int setBit(int n,int m){
        return  n |(1<<(m-1));
    }
    //从低位到高位,将n的第m位置0
    public  static int setBitZero(int n,int m){
        return  n& ~(1<<(m-1));
    }

    //找出不大于N的最大的2的幂指数

    public static int largest_power(int n){
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        return (n+1)>>1;
    }
    //交换符号将正数变成负数，负数变成正数
    //负数在计算机中存放的是补码
     static int reversal(int a) {
        return ~a + 1;
    }
    //二进制中1的个数
    public static int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (n != 0 ) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    public static int NumberOf1_2(int n){
        int count=0;
        while (n!=0){
            count++;
            n &= (n-1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getMaxInt());
        System.out.println(getMaxInt1());
        System.out.println(1<<31);
        System.out.println(-2147483648-1);
        System.out.println(getMaxLong());
        System.out.println((long)1<<64);
        System.out.println(3>>31);
        System.out.println(-~2);
        System.out.println(~2);
        System.out.println(0- ~2);
        System.out.println(NumberOf1(17));
        System.out.println(Integer.toBinaryString(17));
    }
}
