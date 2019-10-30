package com.sf.jkt.k.algorithm.face.cas

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicStampedReference

fun main() {
    val ai=AtomicInteger()
    ai.getAndIncrement()
    testABA2()
}

fun testABA2(){
    val ai=AtomicInteger(100)
    val asr=AtomicStampedReference(100,0)

    val t1=Thread{
        ai.compareAndSet(100,101)
        ai.compareAndSet(101,100)
    }

    val t2=Thread{
        TimeUnit.SECONDS.sleep(1)
        val c3=ai.compareAndSet(100,101)
        println(c3)
    }
    t1.start()
    t1.join()
    t2.start()
    t2.join()

    val t3=Thread{
        TimeUnit.SECONDS.sleep(1)
        asr.compareAndSet(100,101,asr.stamp,asr.stamp+1)
        asr.compareAndSet(101,100,asr.stamp,asr.stamp+1)
    }
    val t4=Thread{
        var stamp=asr.stamp
        TimeUnit.SECONDS.sleep(2)
        // stamp一直在增加，故此处会失败
        val c3=asr.compareAndSet(100,101,stamp,stamp+1)
        println(c3)
    }

    t3.start()
    t4.start()
}

fun testAbA(){
    val asr=AtomicStampedReference(0,0)
    val ref=asr.reference
    var stamp=asr.stamp
    println("reference:"+asr.reference+";stamp:"+asr.stamp)
    val t1=Thread{
       println("reference:"+asr.reference+";stamp:"+asr.stamp+asr.compareAndSet(ref,ref+10,stamp,stamp+1))
    }
    t1.name="thread-1"
    val t2=Thread{
        println("reference:"+asr.reference+";stamp:"+asr.stamp+asr.compareAndSet(ref,ref+10,stamp,stamp+1))
    }
    t1.start()
    t1.join()
    t2.start()
    t2.join()
    println("reference:"+asr.reference+";stamp:"+asr.stamp)
    Thread.sleep(2000)
}