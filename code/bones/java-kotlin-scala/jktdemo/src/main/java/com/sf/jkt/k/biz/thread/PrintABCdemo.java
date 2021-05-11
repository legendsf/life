package com.sf.jkt.k.biz.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCdemo {
   static class PrintDemo1{
       private volatile int flag=0;

       public synchronized void printa()throws Exception{
           while (true){
               if(flag==0){
                   System.out.println("A");
                   flag=1;
                   notifyAll();
               }
               wait();
           }
       }

       public synchronized void printb()throws Exception{
           while (true){
               if(flag==1){
                   System.out.println("A");
                   flag=2;
                   notifyAll();
               }
               wait();
           }
       }
       public synchronized void printc()throws Exception{
           while (true){
               if(flag==2){
                   System.out.println("B");
                   flag=0;
                   notifyAll();
               }
               wait();
           }
       }
   }

   static class Demo2{
       private ReentrantLock lock=new ReentrantLock();
       private Condition lockA=lock.newCondition();
       private Condition lockB = lock.newCondition();
       private Condition lockC=lock.newCondition();
        volatile int flag;
       public void printA()throws Exception{
           lock.lock();
           try{
               while (true){
                   while (flag!=0){
                       lockA.await();
                   }
                   System.out.println("A");
                   flag=1;
                   lockB.signal();
               }
           }finally {
               lock.unlock();
           }
       }

        public void printB()throws Exception{
           lock.lock();
           try{
               while (true){
                   while (flag!=1){
                       lockB.await();
                   }
                   System.out.println("B");
                   flag=2;
                   lockC.signal();
               }
           }finally {
               lock.unlock();
           }
        }

        public void printC()throws Exception{
           lock.lock();
           try{
               while (true){
                   while (flag!=2){
                       lockC.await();
                   }
                   System.out.println("C");
                   flag=0;
                   lockA.signal();
               }
           }finally {
               lock.unlock();
           }
        }
   }

    static class Demo3{
       Semaphore sa = new Semaphore(1);
       Semaphore sb = new Semaphore(0);
       Semaphore sc = new Semaphore(0);
       public void printA(){

       }

       public void print(String name,Semaphore cur,Semaphore next){
           for (int i=0;i<30;i++){
               try{
                   cur.acquire();
                   System.out.println(name);
                   i++;
                    next.release();
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       }

    }


}
