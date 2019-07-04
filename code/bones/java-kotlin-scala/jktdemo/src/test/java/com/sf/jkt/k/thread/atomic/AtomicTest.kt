package com.sf.jkt.k.thread.atomic

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import java.util.concurrent.atomic.AtomicStampedReference
import java.util.concurrent.locks.LockSupport

val thread = Thread {
    println("hello")
    println("world")
    TimeUnit.SECONDS.sleep(50)
}

fun main() {
// testInterrupt()
//    testLockSupport()
    testCLHLock()
}

class SimpleLock {
    private val cas = AtomicReference<Thread>()
    fun lock() {
        val th = Thread.currentThread()
        while (!cas.compareAndSet(null, th)) {
        }
    }

    fun unlock() {
        val th = Thread.currentThread()
        while (!cas.compareAndSet(th, null)) {
        }
    }
}

class ReentrantSpinLock {
    val cas = AtomicReference<Thread>()
    var ai = AtomicInteger()
    fun lock() {
        val th = Thread.currentThread()
        if (th == cas.get()) {
            ai.addAndGet(1)
            return
        }
        while (!cas.compareAndSet(null, th)) {

        }
    }

    // 非原子操作
    fun unlock() {
        val th = Thread.currentThread()
        if (th == cas.get()) {
            ai.decrementAndGet()
        } else {
            cas.compareAndSet(th, null)
        }
    }
}

class TicketLock {
    val serviceNum = AtomicInteger()
    val ticketNum = AtomicInteger()
    fun lock(): Int {
        val currentTicketNum = ticketNum.incrementAndGet()
        while (currentTicketNum != serviceNum.get()) {
        }
        return currentTicketNum
    }

    fun unlock(ticketNum: Int) {
        serviceNum.compareAndSet(ticketNum, ticketNum + 1)
    }

}

class TicketLock2 {
    val serviceNum = AtomicInteger()
    val ticketNum = AtomicInteger()
    val ticketNumHolder = ThreadLocal<Int>()
    fun lock() {
        val currentTicketNum = ticketNum.incrementAndGet()
        ticketNumHolder.set(currentTicketNum)
        while (currentTicketNum != serviceNum.get()) {
        }
    }

    fun unlock() {
        val currentTicketNum = ticketNumHolder.get()
        serviceNum.compareAndSet(currentTicketNum, currentTicketNum + 1)
    }
}

class CLHLock3{
    class Qnode{
        var locked=false
    }
    var tail=AtomicReference<Qnode>(Qnode())
    var node=ThreadLocal<Qnode>()
    constructor(){
        node.set(Qnode())
    }

    fun lock(){
        var node=node.get()
        node.locked=true
        var pre=tail.getAndSet(node)
        //自旋锁，无锁并发
        while (pre.locked){}
    }

    fun unlock(){
        var tnode=node.get()
        tnode.locked=false
    }


}

class CLHLock2 {
    class QNode {
        var locked = false
    }

    val tail = AtomicReference<QNode>(QNode())
    var myPred: ThreadLocal<QNode>
    var myNode: ThreadLocal<QNode>

    constructor() {
        myNode = ThreadLocal<QNode>()
        myNode.set(QNode())
        myPred = ThreadLocal()
    }

    fun lock() {
        val qnode = myNode.get()
        qnode.locked = true
        val pred = tail.getAndSet(qnode)
        while (pred.locked) {
        }
    }

    fun unlock() {
        val qnode = myNode.get()
        qnode.locked = false
        myNode.set(myPred.get())
    }



}


class CLHLock {
    //ERROR
    class CLHNode {
        @Volatile
        var isLocked = true
    }

    @Volatile
    var tail: CLHNode? = null

    companion object {
        val local = ThreadLocal<CLHNode>()
        val updater = AtomicReferenceFieldUpdater.newUpdater(CLHLock::class.java, CLHNode::class.java, "tail")
    }

    fun lock() {
        val node = CLHNode()
        local.set(node)
        var preNode = updater.getAndSet(this, node)
        if (preNode != null) {
            while (preNode.isLocked) {
            }
        }
    }

    fun unlock() {
        var node = local.get()
        if(!updater.compareAndSet(this,node,null)){
            node.isLocked=false
        }
    }
}

fun testLockSupport() {

    LockSupport.unpark(Thread.currentThread())
    LockSupport.park()
    println("hello")
    println("world")
}

fun testAtomicStampedReference() {
    val ai = AtomicStampedReference<Int>(0, 0)
    val cu = ai.reference
    val ti = ai.stamp
    ai.compareAndSet(cu, cu + 1, ti, ti + 1)
}

fun testCLHLock() {
    val clhLock = CLHLock()
    clhLock.lock()
//    clhLock.lock()
//    clhLock.unlock()
    clhLock.unlock()
    clhLock.lock()
    clhLock.unlock()
    TimeUnit.SECONDS.sleep(500)
}

fun testlock() {


    //方法内部，只有为null,可加锁，不为null不可加锁
    //不可重入自旋锁
    val lock = SimpleLock()
    lock.lock()
    lock.unlock()
    val lock2 = ReentrantSpinLock()
    lock2.lock()
    lock2.lock()
    lock2.unlock()
    lock2.unlock()
    val ticketLock = TicketLock()
    ticketLock.lock()
    ticketLock.lock()
    ticketLock.lock()
    ticketLock.unlock(1)
    ticketLock.unlock(2)
    ticketLock.unlock(3)
}

fun testlock2() {

}

fun testInterrupt() {
    println(Thread.currentThread().isInterrupted)
    thread.start()
    println(thread.isInterrupted)
    thread.interrupt()
    println(Thread.interrupted())
    Thread.currentThread().interrupt()
    println(Thread.interrupted())
    println(Thread.currentThread().isInterrupted)
}