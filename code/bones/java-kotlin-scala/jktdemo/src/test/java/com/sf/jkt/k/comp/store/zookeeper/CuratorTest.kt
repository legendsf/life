package com.sf.jkt.k.comp.store.zookeeper

import com.sf.jkt.k.comp.store.zookeeper.latch.getClient
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.api.*
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger
import org.apache.curator.framework.recipes.barriers.DistributedBarrier
import org.apache.curator.framework.recipes.leader.LeaderLatch
import org.apache.curator.framework.recipes.leader.LeaderLatchListener
import org.apache.curator.framework.recipes.leader.LeaderSelector
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter
import org.apache.curator.framework.recipes.locks.InterProcessMutex
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2
import org.apache.curator.framework.recipes.shared.SharedCount
import org.apache.curator.framework.state.ConnectionState
import org.apache.curator.framework.state.ConnectionStateListener
import org.apache.curator.framework.state.StandardConnectionStateErrorPolicy
import org.apache.curator.retry.RetryNTimes
import org.apache.curator.retry.RetryOneTime
import org.apache.curator.utils.CloseableUtils
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooDefs
import java.io.Closeable
import java.lang.Exception
import java.lang.RuntimeException
import java.net.InetAddress
import java.nio.charset.Charset
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class CuratorTest {
    lateinit var zkTools: CuratorFramework
    var watchers = ConcurrentSkipListSet<String>()
    val charset = Charset.forName("utf-8")
    val latchPath = "/curator/latchPath"
    val latchPath2 = "/curator/latchPath2"
    val leaderLatchList = ArrayList<LeaderLatch>(10)
    val leaderSelectorList = ArrayList<CustomLeaderSelectorListenerAdapter>(10)


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


    fun test_init() {
        for (i in 0..10) {
            val latch = LeaderLatch(zkTools, latchPath, "Client #" + i)
            leaderLatchList.add(latch)
            latch.start()
        }
        TimeUnit.SECONDS.sleep(5)
        println("leaderLatch 初始化完成")
    }

    fun test_init1() {
        for (i in 0..10) {
            val latch = LeaderLatch(zkTools, latchPath, "Client #" + i)
            leaderLatchList.add(latch)
            latch.start()
            TimeUnit.SECONDS.sleep(1)
        }
        TimeUnit.SECONDS.sleep(1)
        println("leaderLatch 初始化完成")
    }

    fun test_checkLeader() {
        lateinit var currentLeader: LeaderLatch
        for (tmp in leaderLatchList) {
            if (tmp.hasLeadership()) {
                currentLeader = tmp
                break
            }
        }
        println("当前leader是：" + currentLeader.id)
        currentLeader.close()
        leaderLatchList.remove(currentLeader)
        TimeUnit.SECONDS.sleep(5)
        for (tmp in leaderLatchList) {
            if (tmp.hasLeadership()) {
                currentLeader = tmp
                break
            }
        }
        println("新的leader是：" + currentLeader.id)
        currentLeader.close()
        leaderLatchList.remove(currentLeader)

        val firstLeaderLatch = leaderLatchList.get(0)
        println("删除leader后，当前第一个节点：" + firstLeaderLatch.id)
        firstLeaderLatch.await(10, TimeUnit.SECONDS)
        firstLeaderLatch.await()
        println("after await")
        //close
        for (tmp in leaderLatchList) {
            CloseableUtils.closeQuietly(tmp)
        }
        CloseableUtils.closeQuietly(zkTools)
    }

    fun test_checkLeader2() {
        for (i in 0..10) {
            val leader = CustomLeaderSelectorListenerAdapter(zkTools, latchPath2, "Client #" + i)
            leader.start()
            leaderSelectorList.add(leader)
        }
        TimeUnit.SECONDS.sleep(100)

//        for(tmp in leaderSelectorList){
//            CloseableUtils.closeQuietly(tmp)
//        }
//        CloseableUtils.closeQuietly(zkTools)
    }

}

//公平模式获取LEADER权
class CustomLeaderSelectorListenerAdapter : LeaderSelectorListenerAdapter, Closeable {
    lateinit var name: String
    lateinit var leaderSelector: LeaderSelector
    var leaderCount = AtomicInteger()

    constructor(client: CuratorFramework, path: String, name: String) {
        this.name = name
        this.leaderSelector = LeaderSelector(client, path, this)
        //该实例释放领导权后重新获取排队，等待获取领导权
        leaderSelector.autoRequeue()
    }

    fun start() {
        this.leaderSelector.start()
    }

    override fun takeLeadership(client: CuratorFramework) {
        val waitSeconds = 2L
        println(name + " 成为当前leader")
        println(name + " 之前成为leader的次数:" + leaderCount.getAndIncrement() + "次数")
        try {
            TimeUnit.SECONDS.sleep(waitSeconds)
        } catch (e: InterruptedException) {
            println(name + " 已被中断")
        } finally {
            println(name + " 放弃领导权\n")
        }
    }

    override fun close() {
        leaderSelector.close()
    }
}

fun testLeaderLatch1() {
    val client = getClient()
    client.connectionStateListenable.addListener(object : ConnectionStateListener {
        override fun doNotDecorate(): Boolean {
            return super.doNotDecorate()
        }

        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            //SUSPENED : 过期时间内等待状态，超时则最后变为LOST
            //VERSION AFTER 3.0 LOST MEANT RETRY POLICY HAS EXPIRED
            //LOST:Curator will set the LOST state when it believes that the ZooKeeper session has expired. ZooKeeper connections have a session. When the session expires, clients must take appropriate action. In Curator, this is complicated by the fact that Curator internally manages the ZooKeeper connection. Curator will set the LOST state when any of the following occurs: a) ZooKeeper returns a Watcher.Event.KeeperState.Expired or KeeperException.Code.SESSIONEXPIRED; b) Curator closes the internally managed ZooKeeper instance; c) The session timeout elapses during a network partition. It is possible to get a RECONNECTED state after this but you should still consider any locks, etc. as dirty/unstable. NOTE: The meaning of LOST has changed since Curator 3.0.0. Prior to 3.0.0 LOST only meant that the retry policy had expired.
            if (newState == ConnectionState.LOST || newState == ConnectionState.SUSPENDED) {
                println("connection suspened or lost ,not take leadership")
            }
            //	A suspended or lost connection has been re-established.
            // RETRY AND RECONNECTED
            if (newState == ConnectionState.RECONNECTED) {
                //session 过期后重连，本地缓存取得的数据是过期的
                //所以要删除原有数据，重新设置当前的最新数据
                println("connection reconnected ")
            }
        }
    })
//    client.curatorListenable.addListener()
//    client.connectionStateListenable.addListener()
//    client.unhandledErrorListenable.addListener()
    val leaderLatch = LeaderLatch(client, "/zk/latch", "Client")
    leaderLatch.addListener(object : LeaderLatchListener {
        override fun notLeader() {
            println("i am not a leader  pause or stop business only should do with leaderShip")
        }

        override fun isLeader() {
            println("i am a leader can do things with leaderShip")
        }
    })

//    leaderLatch.addListener(object :LeaderSelectorListenerAdapter(){
//
//        override fun takeLeadership(client: CuratorFramework?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//    })
    leaderLatch.start()

}

fun testLeaderSelector() {
    val client = getClient()
    client.connectionStateListenable.addListener(object : ConnectionStateListener {
        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })
    class Listener(var number: Int) : LeaderSelectorListenerAdapter() {
        override fun takeLeadership(client: CuratorFramework?) {
            println("have leadership before business:" + number)
            TimeUnit.SECONDS.sleep(1)
            println("release leadership:" + number)

        }

        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            super.stateChanged(client, newState)
        }
    }

    for (i in 0..10) {
        val selector = LeaderSelector(client, "/zk/selector", Listener(i))
        selector.autoRequeue()
        selector.start()
    }

}

fun testWatch() {
    val client = getClient()
    if (client.checkExists().forPath("/root") != null) {
        client.delete().deletingChildrenIfNeeded().forPath("/root")
    }
//    client.create().creatingParentContainersIfNeeded().withProtection().inBackground().forPath("")
    client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/root/test4/test4/test4", "test4".toByteArray())
//    val rootChildren1 = client.children.usingWatcher(object : CuratorWatcher {
//        override fun process(event: WatchedEvent?) {
//            println("using watcher 节点：" + event?.path + " 事件类型:" + event?.type)
//        }
//    }).forPath("/root")

    //异步监听器成功失败都会到这里
    client.curatorListenable.addListener(object : CuratorListener {
        override fun eventReceived(client: CuratorFramework?, event: CuratorEvent?) {
            println("using curatorListener 节点:" + event?.path + " 事件类型:" + event?.type)
            throw RuntimeException("I am a exception")
        }
    })

    //handle background exception
    client.unhandledErrorListenable.addListener(object : UnhandledErrorListener {
        override fun unhandledError(message: String?, e: Throwable?) {
            println("errorMsg:" + message + ";exception:" + e?.message)
        }
    })

    client.connectionStateListenable.addListener(object : ConnectionStateListener {
        override fun doNotDecorate(): Boolean {
            return super.doNotDecorate()
        }

        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })

    client.delete().deletingChildrenIfNeeded().forPath("/root/test4/test4/test4")
    //异步
    client.delete().deletingChildrenIfNeeded().inBackground().forPath("/root/test4/test4")
    client.delete().deletingChildrenIfNeeded().inBackground().forPath("/root/test4")

//    client.delete().deletingChildrenIfNeeded().forPath("/root")
//    client.delete().deletingChildrenIfNeeded().inBackground(object :BackgroundCallback{
//        override fun processResult(client: CuratorFramework?, event: CuratorEvent?) {
//            println("using BackgroundCallback节点:" + event?.path + " 事件类型:" + event?.type)
//        }
//    }).forPath("/root")
}

class MyConnectionStateListener(var path: String, var value: String) : ConnectionStateListener {

    override fun stateChanged(client: CuratorFramework, newState: ConnectionState) {
        if (newState == ConnectionState.LOST) {
            while (true) {
                if (client.zookeeperClient.blockUntilConnectedOrTimedOut()) {
                    client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, value.toByteArray())
                }
            }
        }


    }
}

fun testReentrantLock() {
    val client = getClient()
    val lock = InterProcessMutex(client, "/mzk/mutex")
    try {
        lock.acquire()
        TimeUnit.SECONDS.sleep(1)
        println("lock accquire")
        lock.acquire()
        println("lock accquire")
    } finally {
        lock.release()
        println("lock release")
        lock.release()
        println("lock release")
    }
}

fun testSemaphoreMutex() {
    val client = getClient()
    client.connectionStateListenable.addListener(object : ConnectionStateListener {
        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
            if (ConnectionState.LOST == newState || ConnectionState.SUSPENDED == newState) {

            }
        }
    })
    val lock = InterProcessSemaphoreMutex(client, "/zk/sem")
    lock.acquire()
    lock.release()

}

fun testRWlock() {
    val client = getClient()
    val lock = InterProcessReadWriteLock(client, "/zk/rwlock")
    val readLock = lock.readLock()
    val writeLock = lock.writeLock()

    readLock.acquire()
    readLock.release()
    writeLock.acquire()
    writeLock.release()

    val semaphore = InterProcessSemaphoreV2(client, "/zk/sem2", 10)
    val lesses = semaphore.acquire(5)
    semaphore.returnAll(lesses)
    val less1 = semaphore.acquire()
    semaphore.returnLease(less1)

}

fun testSharedCount() {
    val client = getClient()
    //add SharedCountListener
    val baseCount = SharedCount(client, "/zk/sc1", 0)
    baseCount.start()
    val baseCount1 = SharedCount(client, "/zk/sc1", 0)
    baseCount1.start()
    //version 带表client ,更新不成功则重试到更新成功为止,自己写while true
    val flag= baseCount1.trySetCount(baseCount.versionedValue, baseCount.count + 1)
    if(flag){
        println("设置成功,value is:"+baseCount1.count)
    }
    baseCount.close()
    baseCount1.close()
    val count = DistributedAtomicInteger(client, "/zk/di", RetryNTimes(10, 10))
    val value = count.increment()
    if(value.succeeded()){
        println("新增成功:"+"pre:"+value.preValue()+" post:"+value.postValue())
    }else{
        println("更新失败")
    }
    val service= Executors.newFixedThreadPool(10)
    val controlBarrier=DistributedBarrier(client,"/zk/barrier")
    controlBarrier.setBarrier()
    for(i in 0..10){
        val barrier=DistributedBarrier(client,"/zk/barrier")
        service.submit(Callable {
            TimeUnit.MILLISECONDS.sleep(3*Math.random().toLong())
            println("Client#"+i+" waits on Barrier")
            barrier.waitOnBarrier()
            println("Client#"+i+"begins")
        })
    }
    TimeUnit.SECONDS.sleep(5)
    println("all barrier instances should wait the condition")
    controlBarrier.removeBarrier()
    // 不能中断正在执行的线程
    service.shutdown()
    while(!service.awaitTermination(1,TimeUnit.SECONDS)){
        println("线程池还在执行")
    }
    service.awaitTermination(10,TimeUnit.SECONDS)

}

fun main() {
//    testLeaderLatch1()
//    testWatch()
//    testLeaderSelector()
    testSharedCount()
    TimeUnit.SECONDS.sleep(50)

}