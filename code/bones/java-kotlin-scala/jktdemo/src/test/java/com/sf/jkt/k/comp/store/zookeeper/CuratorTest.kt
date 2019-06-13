package com.sf.jkt.k.comp.store.zookeeper

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.api.CuratorWatcher
import org.apache.curator.framework.recipes.leader.LeaderLatch
import org.apache.curator.framework.state.ConnectionState
import org.apache.curator.framework.state.ConnectionStateListener
import org.apache.curator.retry.RetryNTimes
import org.apache.curator.utils.CloseableUtils
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooDefs
import java.lang.Exception
import java.net.InetAddress
import java.nio.charset.Charset
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.TimeUnit

class CuratorTest {
    lateinit var zkTools: CuratorFramework
    var watchers = ConcurrentSkipListSet<String>()
    val charset = Charset.forName("utf-8")

    constructor() {
        zkTools = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .namespace("zk/test")
                .retryPolicy(RetryNTimes(10, 10000))
                .build()
        zkTools.start()
    }

    fun addReconnectionWatcher(path: String, watcherType: ZookeeperTest.ZookeeperWatcherType, watcher: CuratorWatcher) {
        synchronized(this) {
            if (!watchers.contains(watcher.toString())) {
                watchers.add(watcher.toString())
                println("New watcher: " + watcher.toString())
                zkTools.connectionStateListenable.addListener(object : ConnectionStateListener {
                    override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
                        println("stateChanged: " + newState)
                        if (newState == ConnectionState.LOST) {
                            try {
                                if (watcherType == ZookeeperTest.ZookeeperWatcherType.EXIST) {
                                    zkTools.checkExists().usingWatcher(watcher).forPath(path)
                                } else if (watcherType == ZookeeperTest.ZookeeperWatcherType.GET_CHILDREN) {
                                    zkTools.children.usingWatcher(watcher).forPath(path)
                                } else if (watcherType == ZookeeperTest.ZookeeperWatcherType.GET_DATA) {
                                    zkTools.data.usingWatcher(watcher).forPath(path)
                                } else if (watcherType == ZookeeperTest.ZookeeperWatcherType.CREATE_ON_NO_EXIST) {
                                    var stat = zkTools.checkExists().usingWatcher(watcher).forPath(path)
                                    if (stat == null) {
                                        println("to created")
                                        zkTools.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                                                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                                                .forPath(path)
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                })
            }
        }
    }


    fun create() {
        if (zkTools.checkExists().forPath("/zk/test") != null) {
            zkTools.delete().deletingChildrenIfNeeded().forPath("/zk/test")
        }
        zkTools.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/zk/test")
    }

    fun put() {
        zkTools.setData().forPath("/zk/test", "hello world".toByteArray(charset))
    }

    fun get() {
        val wath = ZKWatch("/zk/test")
        val buffer = zkTools.data.usingWatcher(wath).forPath("/zk/test")
        println(String(buffer, charset))
        addReconnectionWatcher("/zk/test", ZookeeperTest.ZookeeperWatcherType.GET_DATA, wath)
    }

    fun register() {
        val ip = InetAddress.getLocalHost().hostAddress
        var registeNode = "/zk/register/" + ip
        var data = "disable".toByteArray(charset)
        var watcher = ZKWatchRegister(registeNode, data)
        var stat = zkTools.checkExists().forPath(registeNode)
        if (stat != null) {
            zkTools.delete().forPath(registeNode)
        }
        zkTools.create().creatingParentsIfNeeded().withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(registeNode, data)
        addReconnectionWatcher(registeNode, ZookeeperTest.ZookeeperWatcherType.CREATE_ON_NO_EXIST, watcher)
        println("get path from zk: " + registeNode + ": " + String(data, charset))
    }

    inner class ZKWatch : CuratorWatcher {
        lateinit var path: String

        constructor(path1: String) {
            this.path = path1
        }

        override fun process(event: WatchedEvent) {
            println(event.type)
            if (event.type == Watcher.Event.EventType.NodeDataChanged) {
                var data = zkTools.data.usingWatcher(this).forPath(path)
                println("process********" + path + ":" + String(data, charset))
            }
        }
    }

    inner class ZKWatchRegister : CuratorWatcher {
        var path: String
        var value: ByteArray

        constructor(path1: String, value1: ByteArray) {
            this.path = path1
            this.value = value1
        }

        override fun process(event: WatchedEvent) {
            println(event.type)
            if (event.type == Watcher.Event.EventType.NodeDataChanged) {
                //节点数据改变了，需要记录下来，以便session过期后，能够恢复到先前的数据状态
                val data = zkTools.data.usingWatcher(this).forPath(path)
                value = data
                println("process********" + path + String(data, charset))
            } else if (event.type == Watcher.Event.EventType.NodeDeleted) {
                //节点被删除了，需要创建新的节点,并存储值
                println("process********" + path + " has been deleted")
                val stat = zkTools.checkExists().usingWatcher(this).forPath(path)
                if (stat == null) {
                    zkTools.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, value)
                }
            } else if (event.type == Watcher.Event.EventType.NodeCreated) {
                //节点被创建时，需要添加监听事件
                //创建可能是由于session过期后,curator的状态监听部分触发的
                println("process********" + path + " has been created" + String(value))
                zkTools.setData().forPath(path, value)
                zkTools.data.usingWatcher(this).forPath(path)
            }
        }
    }

    fun test() {
        val test = CuratorTest()

        test.create()
        test.get()
        test.register()
        Thread.sleep(10000)
    }

    val latchPath = "/curator/latchPath"
    val leaderLatchList = ArrayList<LeaderLatch>(10)
    fun test_init() {
        for (i in 0..10) {
            val latch = LeaderLatch(zkTools, latchPath, "Client #" + i)
            leaderLatchList.add(latch)
            latch.start()
        }
        TimeUnit.SECONDS.sleep(5)
        println("leaderLatch 初始化完成")
    }

    fun test_checkLeader(){
        lateinit var currentLeader:LeaderLatch
        for(tmp in leaderLatchList){
            if(tmp.hasLeadership()){
                currentLeader=tmp
                break
            }
        }
        println("当前leader是："+currentLeader.id)
        currentLeader.close()
        leaderLatchList.remove(currentLeader)
        TimeUnit.SECONDS.sleep(5)
        for(tmp in leaderLatchList){
            if(tmp.hasLeadership()){
                currentLeader=tmp
                break
            }
        }
        println("新的leader是："+currentLeader.id)
        currentLeader.close()
        leaderLatchList.remove(currentLeader)

        val firstLeaderLatch=leaderLatchList.get(0)
        println("删除leader后，当前第一个节点："+firstLeaderLatch.id)
        firstLeaderLatch.await(10,TimeUnit.SECONDS)

        println("after await")
        //close
        for(tmp in leaderLatchList){
            CloseableUtils.closeQuietly(tmp)
        }
        CloseableUtils.closeQuietly(zkTools)
    }
}


fun main() {
    var test=CuratorTest()
    test.test_init()
    test.test_checkLeader()
}