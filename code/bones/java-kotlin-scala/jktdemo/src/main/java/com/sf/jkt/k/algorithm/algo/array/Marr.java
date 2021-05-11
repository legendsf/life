package com.sf.jkt.k.algorithm.algo.array;

import java.util.stream.IntStream;

public class Marr {
    public int index;
    public int size;
    public int[] data;

    public Marr(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not less than 0");
        }
        data = new int[size];
        this.size = size;
        index = 0;
    }

    public void insert(int loc, int n) {
        if (loc >= size) {
            throw new IllegalArgumentException("loc >=size");
        }
        if (++index >= size) {
            resize();
        }
        for (int i = size - 1; i > loc; i--) {
            data[i] = data[i - 1];
            data[loc] = n;
        }

    }

    public void resize() {
        size=2*size;
        int[] newdata = new int[2 * size];//溢出
        System.arraycopy(data, 0, newdata, 0, 2 * size);
    }

    public static void  tesoverflow(){
        int x1 = 2 * Integer.MAX_VALUE;
        System.out.println(x1);
        System.out.println(x1 - Integer.MAX_VALUE);
        System.out.println(x1 - (Integer.MAX_VALUE - 1));
        System.out.println(-1 - Integer.MAX_VALUE);
    }


    public static void main(String[] args) {
        System.out.println(fab3(1,5));
        System.out.println(fab5(5));

        int[] data=new int[41];
        IntStream.range(0,40).forEach(i->{
            long start=System.currentTimeMillis();
//            System.out.println(fab2(i,data)+" "+(System.currentTimeMillis()-start)+" ms");
            System.out.println(fab3(1,i));
        });
    }

    public static  int fab5(int n){
        if(n<=2){
            return 1;
        }
        int c=1;
        int a=1;
        int b=1;
        for (int i=3;i<=n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return  c;
    }

    public static  int fab3(int result,int n){
        if(n<=1){
            return  result;
        }
        return fab3(result*n,n-1);
    }

    public  static  int fab4(int n,int first,int second){
        if(n<=1){
            return  first;
        }
        return fab4(n-1,second,first+second);
    }

    public static int fab2(int n,int[] data){
        if(n<=2){
            return 1;
        }
        if(data[n]!=0){
            return  data[n];
        }
        int res=fab2(n-1,data)+fab2(n-2,data);
        data[n]=res;
        return res;
    }

    public  static int fab(int n){
        if(n<2){
            return  1;
        }
        return  fab(n-1)+fab(n-2);
    }

    public  static int notFab(int n){
        if(n<=2){
            return 1;
        }
        int a=1;
        int b=1;
        int c=1;
        for(int i=3;i<=n;i++){
           c=a+b;
           a=b;
           b=c;
        }
        return  c;
    }



}
