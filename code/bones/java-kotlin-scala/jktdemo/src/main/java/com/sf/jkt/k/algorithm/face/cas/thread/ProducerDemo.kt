package com.sf.jkt.k.algorithm.face.cas.thread

import java.util.concurrent.ArrayBlockingQueue

fun main() {
    var abq=ArrayBlockingQueue<String>(3);
    abq.put("a")
    abq.put("b")
    abq.put("c")
    abq.take();
    abq.put("d")
    println(abq.take())
}