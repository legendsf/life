package com.sf.jkt.k.algorithm.round;

public class RequestId {
    public static Integer num=0;
    public static final int getAndIncrement(){
        return num++;
    }
}
