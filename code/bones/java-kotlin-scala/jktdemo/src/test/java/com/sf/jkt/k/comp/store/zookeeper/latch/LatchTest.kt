package com.sf.jkt.k.comp.store.zookeeper.latch

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.leader.LeaderLatch
import org.apache.curator.framework.state.StandardConnectionStateErrorPolicy
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.server.quorum.Leader

var mLeaderLatch="/zk/latch"

fun getClient(): CuratorFramework {
    val client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .connectionTimeoutMs(5000)
            .sessionTimeoutMs(5000)
            .retryPolicy(ExponentialBackoffRetry(5000, 3))
            .connectionStateErrorPolicy(StandardConnectionStateErrorPolicy())
            .build()
    client.start()
    client.blockUntilConnected()
    return client
}

fun getLeaderLatch(id:Int) :LeaderLatch{
    val client= getClient()
//    val leaderLatch=LeaderLatch(client, mLeaderLatch,"Client#"+id)
    val leaderLatch=LeaderLatch(client, mLeaderLatch)
    return leaderLatch
}

fun await(leaderLatch: LeaderLatch){
    leaderLatch.start()
    leaderLatch.await()
//    leaderLatch.close()
}
