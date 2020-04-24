package com.sf.jkt.k.algorithm.thread;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    ThreadLocal local = new ThreadLocal();
    public void testLocal(){
        local.set("xxx"+Thread.currentThread().getName());
        System.out.println(local.get());
        local.remove();

    }

    public static void main(String[] args)throws Exception {
        ThreadLocalTest tt = new ThreadLocalTest();
       new Thread(()->{tt.testLocal();}).start();
        new Thread(()->{tt.testLocal();}).start();
        TimeUnit.SECONDS.sleep(100);
    }
}
