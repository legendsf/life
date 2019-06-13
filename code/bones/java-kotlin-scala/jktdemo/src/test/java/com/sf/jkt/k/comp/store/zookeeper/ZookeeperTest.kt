package com.sf.jkt.k.comp.store.zookeeper

import org.apache.curator.RetryPolicy
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.data.Stat
import org.junit.Before
import org.junit.Test
import org.apache.curator.framework.api.transaction.CuratorTransactionResult
import com.fasterxml.jackson.core.JsonPointer.forPath
//import org.apache.curator.framework.api.transaction.CuratorOp
import java.util.concurrent.Executors
import com.fasterxml.jackson.core.JsonPointer.forPath
import com.google.gson.Gson
import org.apache.curator.framework.api.CuratorEvent
import org.apache.curator.framework.api.CuratorListener
import org.apache.curator.framework.api.CuratorWatcher
import org.apache.curator.framework.recipes.cache.*
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode
import org.apache.tomcat.jni.File.getStat
import org.apache.zookeeper.ZooDefs.OpCode.getData
import sun.font.LayoutPathImpl.getPath
import org.apache.curator.retry.RetryNTimes
import org.apache.curator.utils.CloseableUtils
import org.apache.zookeeper.WatchedEvent
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event
import org.apache.zookeeper.ZooDefs.OpCode.getData
import sun.font.LayoutPathImpl.getPath


class ZookeeperTest {

    @Test
    fun test_watcher_error() {
        val client = getZkClient1()
        client.start()
        if (client.checkExists().forPath("/watcher") != null) {
            client.delete().deletingChildrenIfNeeded().forPath("/watcher")
        }
        client.create().forPath("/watcher", "watcher".toByteArray())
        val watcher = PathChildrenCache(client, "/watcher", true)
        val listener = object : PathChildrenCacheListener {
            override fun childEvent(p0: CuratorFramework?, p1: PathChildrenCacheEvent?) {
                val data = p1?.data
                if (data == null) {
                    println("No data in event[" + p1 + "]")
                } else {
                    println("Receive event: type=" + p1.type + " path=" + data.path + " data=" + String(data.data) + " stat=" + data.stat)
                }
            }
        }


        watcher.listenable.addListener(listener)
        watcher.start(StartMode.BUILD_INITIAL_CACHE)
        println("Register zk watcher success fully")
        Thread.sleep(30000L)
    }


    fun getZkClient(): CuratorFramework {
        var client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",
                6000, 6000,
                ExponentialBackoffRetry(5000, 3))
        return client
    }

    fun getZkClient1(): CuratorFramework {
        var client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(ExponentialBackoffRetry(1000, 3))
//                .namespace("base")
                .build()
        return client
    }

    fun getZkClient1(path: String): CuratorFramework {
        var client = CuratorFrameworkFactory.builder().connectString(path)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(ExponentialBackoffRetry(1000, 3))
//                .namespace("base")
                .build()
        return client
    }

    @Test
    fun test_zookeeper() {
        val client = getZkClient1()
        client.start()
        client.delete().deletingChildrenIfNeeded().forPath("/path")
        client.create().forPath("/path")
        client.create().forPath("/path/ch1", "init".toByteArray())
        var stat = Stat()
        val data = client.data.storingStatIn(stat).forPath("/path/ch1")
        println(stat)
        println(String(data))

    }

    @Test
    fun test_zk1() {
        val client = getZkClient1()
        client.start()
        client.delete().deletingChildrenIfNeeded().forPath("/path")
        client.create().forPath("/path")
        client.create().forPath("/path/ch1")
    }

    @Test
    fun test_zk2() {
        val client = getZkClient1()
        client.start()
        client.create().forPath("/good/g1")
    }

    @Test
    fun test_transaction() {
        val client = getZkClient1()
        client.start()
        client.delete().deletingChildrenIfNeeded().forPath("/path")
        client.create().forPath("/path")
//        val createOp = client.transactionOp().create().forPath("/path/ch1", "some data".toByteArray())
//        val createOp1 = client.transactionOp().create().forPath("/path/ch2", "ch2".toByteArray())
//        val setOp = client.transactionOp().setData().forPath("/path/ch1", "good".toByteArray())
//        val deleteOp = client.transactionOp().delete().forPath("/path/ch2")
//        val results = client.transaction().forOperations(createOp, createOp1, setOp, deleteOp)
//        val createOp1=client.transactionOp().create().forPath("/path/ch2","ch2".toByteArray())
//        val setDataOp = client.transactionOp().setData().forPath("/path/ch2", "other data".toByteArray())
//        val deleteOp = client.transactionOp().delete().forPath("/path/ch3")
//
//        val results = client.transaction().forOperations(createOp,createOp1, setDataOp, deleteOp)

//        for (result in results) {
//            println(result.getForPath() + " - " + result.getType())
//        }

    }

    @Test
    fun test_async() {
        val executor = Executors.newFixedThreadPool(2)
        val client = getZkClient1()
        client.start()
        if (client.checkExists().forPath("/async") != null) {
            client.delete().deletingChildrenIfNeeded().forPath("/async")
        }
        client.curatorListenable.addListener(object : CuratorListener {
            override fun eventReceived(client: CuratorFramework?, event: CuratorEvent?) {
                println("ASYNCLISTENER: " + Gson().toJson(event))
            }
        })

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground({ curatorFramework, curatorEvent ->
                    println(String.format("eventType:%s,resultCode:%s",
                            curatorEvent.type, curatorEvent.resultCode))
                }, executor)
                .forPath("/async")

        Thread.sleep(20000L)
    }

    @Test
    fun test_listener() {
        val client = getZkClient1()
        client.start()
        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/root/test4/test4/test4", "test4".toByteArray())
//        val rootChildren1 = client.children.usingWatcher(object : CuratorWatcher {
//            override fun process(event: WatchedEvent?) {
//                println("usingWatcher 节点:" + event?.path + " 事件类型:" + event?.type)
//            }
//        }).forPath("/root")
        val rootChildren2 = client.children.watched().forPath("/root")
        client.curatorListenable.addListener(object : CuratorListener {
            override fun eventReceived(client: CuratorFramework?, event: CuratorEvent?) {
                println("watched节点:" + event?.path + "事件类型：" + event?.type)
            }
        })
//        client.delete().deletingChildrenIfNeeded().forPath("/root/test4/test4/test4")
        client.delete().deletingChildrenIfNeeded().forPath("/root")


    }

    @Test
    fun test_nodecache() {
        lateinit var nodeCache: NodeCache
        lateinit var client: CuratorFramework
        try {
            val path = "/node_cache_1"
            client = getZkClient1()
            client.start()
            if (client.checkExists().forPath(path) == null) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(path, "init".toByteArray())
            }
            nodeCache = NodeCache(client, path, false)
            """
                其中调用NodeCache的start方法传入true表示第一次启动就立刻从zookeeper上读取相应节点的数据内容，并保存在cache中。
                结果如下，可以看出，修改节点数据会触发事件，删除节点也会，但是增加子节点却不会
            """.trimIndent()
            nodeCache.start(true)
            nodeCache.listenable.addListener(object : NodeCacheListener {
                override fun nodeChanged() {
                    lateinit var str: String
                    if (nodeCache == null || nodeCache.currentData == null || nodeCache.currentData.data == null) {
                        str = ""
                    } else {
                        str = String(nodeCache.currentData.data)
                    }
                    println("nodeChanged:" + str)
                }
            })
            client.setData().forPath(path, "change_data".toByteArray())
            Thread.sleep(1000)
            client.create().creatingParentContainersIfNeeded().forPath(path + "/test")
            Thread.sleep(1000)
            client.delete().deletingChildrenIfNeeded().forPath(path)
            Thread.sleep(1000)
        } finally {
            CloseableUtils.closeQuietly(nodeCache)
            CloseableUtils.closeQuietly(client)
        }
    }

    @Test
    fun test_pathChildrenCache() {
        val path = "/path_children_cache_1"
        val pathSon = path + "/son"
        lateinit var client: CuratorFramework
        lateinit var pathChildrenCache: PathChildrenCache
        try {
            client = getZkClient()
            client.start()
            if (client.checkExists().forPath(pathSon) != null) {
                client.delete().deletingChildrenIfNeeded().forPath(path)
            }
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(pathSon, "init".toByteArray())
            pathChildrenCache = PathChildrenCache(client, path, false)
            pathChildrenCache.start(true)
            pathChildrenCache.listenable.addListener(object : PathChildrenCacheListener {
                override fun childEvent(client: CuratorFramework?, event: PathChildrenCacheEvent?) {
                    println("sonNodeChanged:" + event?.type)
                }
            })
            client.setData().forPath(path, "root".toByteArray())
            Thread.sleep(1000)
            client.setData().forPath(pathSon, "change_data".toByteArray())
            Thread.sleep(1000)
            client.create().creatingParentContainersIfNeeded().forPath(path + "/test", "test".toByteArray())
            Thread.sleep(1000)
            client.delete().deletingChildrenIfNeeded().forPath(pathSon)
            Thread.sleep(1000)
        } finally {
            CloseableUtils.closeQuietly(pathChildrenCache)
            CloseableUtils.closeQuietly(client)
        }
    }

    @Test
    fun test_treeCache() {
        val path = "/tree_cache_1"
        val pathSon = path + "/son"
        val executor = Executors.newFixedThreadPool(2)
        lateinit var client: CuratorFramework
        lateinit var treeCache: TreeCache
        try {
            client = getZkClient1()
            client.start()
            if (client.checkExists().forPath(path) != null) {
                client.delete().deletingChildrenIfNeeded().forPath(path)
            }
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(pathSon, "init".toByteArray())
            treeCache = TreeCache(client, path)
            treeCache.start()
            treeCache.listenable.addListener(object : TreeCacheListener {
                override fun childEvent(client: CuratorFramework?, event: TreeCacheEvent) {
                    println(if (event?.getData() == null) "" else event.getData().path + "监听到事件:" + event.getType())
                    if (event.type == TreeCacheEvent.Type.CONNECTION_LOST) {

                    }
                }
            }, executor)
            client.setData().forPath(path, "root".toByteArray())
            Thread.sleep(1000)
            client.setData().forPath(pathSon, "change_data".toByteArray())
            Thread.sleep(1000)
            client.create().creatingParentContainersIfNeeded().forPath(path + "/test")
            Thread.sleep(1000)
            client.delete().deletingChildrenIfNeeded().forPath(path)
            Thread.sleep(1000)
        } finally {
            CloseableUtils.closeQuietly(treeCache)
            CloseableUtils.closeQuietly(client)
        }
    }


    enum class ZookeeperWatcherType {
        GET_DATA, GET_CHILDREN, EXIST, CREATE_ON_NO_EXIST
    }

    @Test
    fun test_zk_data() {
        val client = getZkClient1("127.0.0.1:6181")
        client.start()
        var ch = client.children.forPath("/")
        println(ch)
        var ch1 = client.children.forPath("/zookeeper")
        var ch2 = client.children.forPath("/elastic-job-lite-springboot")
        println(ch1)
        println(ch2)
        ls(client,"/","")
    }

    fun ls(client: CuratorFramework, path: String,data:String) {
        println(path+"|data="+data)
        var childrens = client.children.forPath(path)
        var data1=String(client.data.forPath(path))
        if (childrens.isNullOrEmpty()) {
            return
        }
        for (s in childrens) {
            val s1 = s.toString()
            if (path.equals("/")) {
                ls(client, path + s1,data1)
            } else {
                ls(client, path + "/" + s1,data1)
            }
        }

    }

}

data class ZD(var path: String, var data: String) {}
