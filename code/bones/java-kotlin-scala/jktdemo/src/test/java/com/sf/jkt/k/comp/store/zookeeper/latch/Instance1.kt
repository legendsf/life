package com.sf.jkt.k.comp.store.zookeeper.latch

import java.util.concurrent.TimeUnit

fun main() {
    val leaderLatch= getLeaderLatch(1)
    await(leaderLatch)
    TimeUnit.SECONDS.sleep(500)
}