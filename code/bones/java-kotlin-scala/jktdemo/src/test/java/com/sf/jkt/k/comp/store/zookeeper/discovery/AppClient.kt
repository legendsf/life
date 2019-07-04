package com.sf.jkt.k.comp.store.zookeeper.discovery

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder

fun test_getServiceByName(){
   val client=CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
           .sessionTimeoutMs(5000)
           .connectionTimeoutMs(5000)
           .retryPolicy(ExponentialBackoffRetry(5000,3))
           .build()
   client.start()
   //IntterupttedException
   client.blockUntilConnected()
   val serviceDiscovery=ServiceDiscoveryBuilder.builder(ServiceDetail::class.java)
           .client(client)
           .basePath(ServiceDetail.REGISTER_ROOT_PATH)
           .build()
   serviceDiscovery.start()

   //query

   val services=serviceDiscovery.queryForInstances("tomcat")
   for(service in services){
      println(service.payload)
      println("ServiceAddress: "+service.address+":"+service.port)
   }
   serviceDiscovery.close()
   client.close()

}

fun main() {
   test_getServiceByName()
}