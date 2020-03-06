package com.sf.jkt.k.algorithm.algo.array05;

public class MArray1 {
    Object[] data;
    int size;

    public MArray1(int capacity){
        data=new Object[capacity];
    }

    public int capacity(){
        return data.length;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

}
