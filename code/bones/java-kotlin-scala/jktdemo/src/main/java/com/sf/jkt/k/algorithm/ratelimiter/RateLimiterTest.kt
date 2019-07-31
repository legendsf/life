package com.sf.jkt.k.algorithm.ratelimiter

import com.google.common.util.concurrent.RateLimiter
import java.util.concurrent.TimeUnit

fun testRateLimiter(){
   var r=RateLimiter.create(5.0)
   while (true){
      println("get 1 tokens:"+r.acquire()+"s")
   }
}

fun testSmooth(){
   var r=RateLimiter.create(2.0)
   while (true){
      println("get 1 tokens:"+r.acquire()+"s")
      TimeUnit.SECONDS.sleep(2)
      println("get 1 tokens:"+r.acquire()+"s")
      println("get 1 tokens:"+r.acquire()+"s")
      println("get 1 tokens:"+r.acquire()+"s")

   }


}
fun testSmooth3(){
   var r = RateLimiter.create(5.0)
   while (true){
      println("get 5 tokens:"+r.acquire(5)+"s")
      println("get 1 tokens:"+r.acquire(1)+"s")
      println("get 1 tokens:"+r.acquire(1)+"s")
      println("get 1 tokens:"+r.acquire(1)+"s")
   }
}

fun testSmoothWaringUp(){
   var r=RateLimiter.create(2.0,3L,TimeUnit.SECONDS)
   while (true){
      System.out.println("get 1 tokens: " + r.acquire(1) + "s");
      System.out.println("get 1 tokens: " + r.acquire(1) + "s");
      System.out.println("get 1 tokens: " + r.acquire(1) + "s");
      System.out.println("get 1 tokens: " + r.acquire(1) + "s");
      System.out.println("end");
   }
}

fun main() {
   testSmoothWaringUp()
}