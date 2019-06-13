package com.sf.jkt.k.util

import com.sf.jkt.k.entity.User
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode
import org.apache.curator.framework.state.ConnectionState
import org.apache.curator.framework.state.ConnectionStateListener
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.retry.RetryNTimes
import org.apache.curator.test.TestingServer

val ZK_ADDRESS = "127.0.0.1:2181"
val ZK_PATH = "f:/tmp/zookeeper"

fun main() {
    test_zookeeper1()
}

fun testUser() {
    val user = User(1, "sfname", "sfpass")
    println(user)
    val (id, name, pass) = user
    println("" + id + ":" + name + ":" + pass)
}

fun test_zookeeper1() {
    val server = TestingServer()
    println(server.connectString)
    val client = CuratorFrameworkFactory.newClient(server.connectString, ExponentialBackoffRetry(1000, 3))
    client.connectionStateListenable.addListener(object :ConnectionStateListener{
        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            println("client state:"+newState?.name)
        }
    })
    client.start()
}

fun test_zookeeper() {
    val client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, RetryNTimes(10, 5000))
    client.start()
    client.delete().deletingChildrenIfNeeded().forPath("/parent")
    println("zookeeper started !")
    val t = client.create().forPath("/parent")
    println(t::class)
    println(t)
    val t1 = client.create().forPath("/parent/children1", "mchild1".toByteArray())
    println(t1)

}