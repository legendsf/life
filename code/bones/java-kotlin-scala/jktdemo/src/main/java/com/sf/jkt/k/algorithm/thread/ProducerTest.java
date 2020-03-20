package com.sf.jkt.k.algorithm.thread;

import java.util.LinkedList;

public class ProducerTest {
    private LinkedList<Object> factory=new LinkedList<>();
    private Object lock = new Object();
    volatile boolean producerStop=false;
    volatile  boolean consumerStop=false;

    public void produce(){
        synchronized (lock){
            while (factory.size()>=10){
                System.out.println("仓库已满");
                try{
                    lock.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            factory.addFirst(new Object());
            System.out.println("生成衣服，现在数量："+factory.size());
            lock.notifyAll();
        }
    }

    public void  consume(){
        synchronized (lock){
            while (factory.size()<=0){
                System.out.println("仓库为空");
                try {
                    lock.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            factory.removeLast();
            System.out.println("消费：现在存量:"+factory.size());
            lock.notifyAll();
        }
    }

    Thread producer=new Thread(()->{
        while (!producerStop){
            produce();
        }
    });

    Thread consumer = new Thread(()->{
        while (!consumerStop){
            consume();
        }
    });

    public static void main(String[] args) {
        ProducerTest test = new ProducerTest();
        test.consumer.start();
        test.producer.start();
    }
}
