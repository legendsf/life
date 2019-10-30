package com.sf.jkt.k.algorithm.face.cas

import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.LockSupport
import java.util.concurrent.locks.ReentrantLock

fun main() {
//    testInterruptBlocked()
//    testInterruptUnblocked()
//    testInterruptSynchronized()
}

/***
 *  wait/notify/notifyall 必须拿到对象监视器的锁
 *  synchronized 可以获取到monitor
 *  所以必须在synchronized 块中
 *  wait 和 sleep 都释放cpu
 *  wait释放锁，sleep不释放锁
 */
fun testSyn() {
    val obj = java.lang.Object()
    synchronized(obj) {
        obj.wait()
        obj.notify()
        obj.notifyAll()
    }
}

fun testInterruptBlocked() {
    val t1 = object : Thread() {
        override fun run() {
            // while在try中，通过异常中断就可以退出run循环
            try {
                while (true) {
                    // 当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                    TimeUnit.SECONDS.sleep(2)
                }
            } catch (e: InterruptedException) {
                println("Interruted When Sleep")
                val interrupt = this.isInterrupted
                // 中断状态被复位
                println("interrupt:$interrupt")
            }

        }
    }
    t1.start()
    TimeUnit.SECONDS.sleep(2)
    // 中断处于阻塞状态的线程
    t1.interrupt()

}

fun testInterruptUnblocked() {
    val t1 = Thread {
        while (true) {
            println("未被中断")
        }
    }
    t1.start()
    TimeUnit.SECONDS.sleep(2)
    t1.interrupt()
}

fun testInterruptSynchronized() {
    val sb = SynchronizedBlocked()
    val t1 = Thread(sb)
    t1.start()
    TimeUnit.SECONDS.sleep(1)
    //已进入f1方法 synchronized 块中
    t1.interrupt()
}

class SynchronizedBlocked : Runnable {
    @Synchronized
    fun f1() {
        println("trying to call f")
        while (true) {
            //礼让其他线程
            Thread.yield()
        }
    }

    override fun run() {
        while (true) {
            if (Thread.interrupted()) {
                println("中断线程")
                break
            } else {
                //测试f1 是否响应中断,如果响应中断那么会走到上面的中断这个逻辑
                f1()
            }
        }
    }

   fun testInterruptLock(){
       val lock=ReentrantLock()
       lock.lock()
       lock.unlock()
       lock.lockInterruptibly()
       lock.unlock()
       lock.tryLock(1,TimeUnit.SECONDS)
       lock.unlock()
       LockSupport.parkNanos(1000)
       LockSupport.unpark(Thread.currentThread())
//       LockSupport.unpark()
       val semaphore=Semaphore(1)
       semaphore.acquire()
       semaphore.release()

       val cond=lock.newCondition()
       cond.await()
       cond.signalAll()

   }

}

