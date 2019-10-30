package com.sf.jkt.k.algorithm.face.cas

import java.util.concurrent.atomic.AtomicReference


class CLH{
    class Node{
        var locked=false
    }
    var node=ThreadLocal<Node>()
    var tail=AtomicReference<Node>(Node())

    constructor(){
        node.set(Node())
    }

    fun lock(){
        var node=node.get()
        node.locked=true
        var pre=tail.getAndSet(node)
        while (pre.locked){}
    }
// t3.pre=t2,t2.pre=t1
// t3 wait t2 释放锁 t2 等待t1释放锁

    fun unlock(){
        var tnode=node.get()
        tnode.locked=false
    }

}