package com.sf.jkt.k.comp.store.zookeeper.discovery

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder
import org.apache.curator.x.discovery.ServiceInstance
import org.apache.curator.x.discovery.ServiceInstanceBuilder
import org.apache.curator.x.discovery.details.JsonInstanceSerializer
import org.apache.zookeeper.KeeperException
import java.lang.RuntimeException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

 class ServiceDetail {
    lateinit var desc:String
     var weight:Int=0
     constructor(){
     }
    constructor(desc:String,weight: Int){
        this.desc=desc
        this.weight=weight
    }
    companion object{
        val REGISTER_ROOT_PATH = "/mall"
    }


}

fun startServiceDiscovery(ip:String ,port:Int,weight: Int){
    val client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .connectionTimeoutMs(5000)
            .sessionTimeoutMs(5000)
            .retryPolicy(ExponentialBackoffRetry(5000, 3))
            .build()
    client.start()

    try {
        if(!client.blockUntilConnected(5000*3,TimeUnit.MILLISECONDS)){
            client.close()
            throw KeeperException.OperationTimeoutException()
        }
    } catch (e: Exception) {
        //没有连接上，没有关闭成功，那么就抛出
        throw RuntimeException(e)
    }

    val sib=ServiceInstance.builder<ServiceDetail>()
    sib.address(ip)
    sib.port(port)
    sib.name("tomcat")
    sib.payload(ServiceDetail("主站程序",weight))

    val instance=sib.build()
    val serviceDiscovery=ServiceDiscoveryBuilder.builder(ServiceDetail::class.java)
            .client(client)
            .serializer(JsonInstanceSerializer<ServiceDetail>(ServiceDetail::class.java))
            .basePath(ServiceDetail.REGISTER_ROOT_PATH)
            .build()
    serviceDiscovery.registerService(instance)
    serviceDiscovery.start()
    println("seriveDiscorvery start !")
    TimeUnit.SECONDS.sleep(700)
    serviceDiscovery.close()
    client.close()

}



fun main() {

    val executor= Executors.newFixedThreadPool(2)
//    executor.execute{println("  ")}
    executor.execute { startServiceDiscovery("127.0.0.1",8081,1) }
    executor.execute { startServiceDiscovery("127.0.0.1",8082,2) }
    executor.awaitTermination(800L,TimeUnit.SECONDS)

}