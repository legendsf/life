package com.sf.jkt.k.Util;

import com.google.common.collect.Lists;

import java.util.List;

public class JVMtest {
    public static void main(String[] args) {
        System.out.println(Integer.toOctalString(11));
        System.out.println(Integer.toHexString(11));
        testMetaSpace();
    }

    /**
     *  -XX:MetaspaceSize=4M -XX:MaxMetaspaceSize=10M  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof  -XX:+PrintGC
     */
    public  static  void testMetaSpace(){
        List<String> str= Lists.newArrayList();
        int i=0;
        while (true){
            str.add(String.valueOf(i).intern());
        }
    }

    public static void testCpuBusy(){
        while (true){

        }
    }



}
