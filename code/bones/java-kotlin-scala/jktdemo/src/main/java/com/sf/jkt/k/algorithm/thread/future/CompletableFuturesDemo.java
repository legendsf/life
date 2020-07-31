package com.sf.jkt.k.algorithm.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFuturesDemo {
    public static void main(String[] args) throws Exception{
        testCompletableFuture();
    }

    public static void testCompletableFuture() throws Exception{
        ExecutorService exec= Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> f= CompletableFuture.supplyAsync(new MySupplier(),exec);
        System.out.println(f.isDone());
        CompletableFuture<Integer> f2=f.thenApply(new PlusOne());
        System.out.println(f2.get());
    }

    public static class MySupplier implements Supplier<Integer>{
        @Override
        public Integer get() {
           try{
               Thread.sleep(1000);
           }catch (Exception e){

           }
           return 1;
        }
    }

    public static class  PlusOne implements Function<Integer,Integer> {
        @Override
        public Integer apply(Integer integer) {
            return integer+1;
        }

    }










}
