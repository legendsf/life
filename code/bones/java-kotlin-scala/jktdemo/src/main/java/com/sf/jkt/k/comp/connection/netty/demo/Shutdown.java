package com.sf.jkt.k.comp.connection.netty.demo;

import sun.misc.Signal;

import java.util.concurrent.TimeUnit;

public class Shutdown {
    public static void main(String[] args) throws Exception{
//        shutdown();
        shutdown2();
        shutdown();
    }

    public static void  shutdown2()throws Exception{
        Signal sig=new Signal("TERM");
        Signal.handle(sig,(s)->{
            System.out.println("Signal handle start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void shutdown()throws Exception{
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("shutdown hook execute start");
            System.out.println("NioEventLoop shutdownGracefully");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("shutdown end");
        }));
        TimeUnit.SECONDS.sleep(7);
//        System.exit(0);
    }
}
