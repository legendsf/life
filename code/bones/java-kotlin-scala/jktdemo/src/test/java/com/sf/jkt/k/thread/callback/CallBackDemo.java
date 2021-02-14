package com.sf.jkt.k.thread.callback;

import com.google.common.util.concurrent.*;
import io.netty.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/***
 * 推荐： completableFuture
 * https://www.cnblogs.com/xinde123/p/10928091.html
 */

@Slf4j
public class CallBackDemo {


    public static void main(String[] args) throws Exception{
//        testCompletableFuture();
//        testGuavaListenableFuture();
        testNettyCallback();
    }

    public static void testNettyCallback()throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        EventExecutorGroup group = new DefaultEventExecutor();
        Future<Integer> future= group.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
               System.out.println("耗时操作");
               timeConsumingOperation();
               return 100;
            }
        });
        future.addListener(new FutureListener<Integer>(){
            @Override
            public void operationComplete(Future<Integer> integerFuture) throws Exception {
                System.out.println("结果："+integerFuture.get());
            }
        });
        stopWatch.prettyPrint();
        new CountDownLatch(1).await();

    }

    public static void testGuavaListenableFuture()throws Exception{
        long l = System.currentTimeMillis();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            }
        });//<1>
        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println("结果: "+integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("exception: ",throwable);
            }
        },Executors.newSingleThreadExecutor());
        future.addListener(()->{
            System.out.println("hello");
        },Executors.newSingleThreadExecutor());
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
        new CountDownLatch(1).await();
    }

    public static void testCompletableFuture()throws Exception{
        long l=System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println("执行耗时操作...");
            timeConsumingOperation();
            return 100;
        });
//        completableFuture.whenComplete((result,e)->{
//            System.out.println("结果： "+result);
//        });
        completableFuture.whenCompleteAsync((r,e)->{
            System.out.println("结果: "+r);
        });
        System.out.println("主线程运算耗时："+(System.currentTimeMillis()-l)+" ms");
        new CountDownLatch(1).await();
    }
    public static void timeConsumingOperation(){
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            log.error("err",e);
        }
    }
}
