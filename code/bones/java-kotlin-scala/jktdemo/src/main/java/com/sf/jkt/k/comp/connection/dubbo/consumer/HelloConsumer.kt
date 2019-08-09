package com.sf.jkt.k.comp.connection.dubbo.consumer

import com.sf.jkt.k.comp.connection.dubbo.api.IHello
import org.apache.dubbo.config.annotation.Reference
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

//@DependsOn("helloServiceImpl")
//@Component
class HelloConsumer {
    @Reference(url = "dubbo://127.0.0.1:20880")
    lateinit var iHello: IHello
    fun hello() {
        val msg = iHello.hello()
        println("consume msg:" + msg)
    }
}