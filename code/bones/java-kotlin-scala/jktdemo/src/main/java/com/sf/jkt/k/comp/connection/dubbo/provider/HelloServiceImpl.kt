package com.sf.jkt.k.comp.connection.dubbo.provider

import com.sf.jkt.k.comp.connection.dubbo.api.IHello
import org.apache.dubbo.config.annotation.Service
import org.springframework.stereotype.Component

@Service(interfaceClass = IHello::class)
@Component
class HelloServiceImpl:IHello{
    override fun hello(): String {
        return "hello-dubbo"
    }
}