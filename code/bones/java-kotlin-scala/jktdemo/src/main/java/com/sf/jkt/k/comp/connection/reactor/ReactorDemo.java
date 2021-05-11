package com.sf.jkt.k.comp.connection.reactor;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * flux 有阻塞行为。
 *
 */
public class ReactorDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("mono异步调用开始："+System.currentTimeMillis());
        Mono.fromCallable(() -> getData())
                .map(s -> s + " World ")
                .subscribe(s -> System.out.println(s));
        System.out.println("mono异步调用结束："+System.currentTimeMillis());
        System.out.println("completablefuture异步调用开始:"+System.currentTimeMillis());
        CompletableFuture.supplyAsync(() -> getData())
                .thenAccept(System.out::println);
        System.out.println("completablefuture异步调用结束:"+System.currentTimeMillis());
        System.out.println(Thread.currentThread()+" End ");
    }

    private static String getData() {

        int j=0;

        for(int i=0; i<Integer.MAX_VALUE; i++){
            j = j - i%2;
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread()+" - "+j);
        return " Hello ";
    }
}
