package com.sf.jkt.k.comp.javaagent.myagent

import net.bytebuddy.agent.builder.AgentBuilder.*
import net.bytebuddy.agent.builder.AgentBuilder.Default
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import net.bytebuddy.implementation.bind.annotation.*
import net.bytebuddy.matcher.ElementMatchers
import net.bytebuddy.utility.JavaModule
import java.lang.instrument.Instrumentation
import java.lang.reflect.Method
import java.util.concurrent.Callable


object MyAgent {

    @JvmStatic
    fun premain(agentArgs: String, inst: Instrumentation) {
        println("this is an perform monitor agent.")

        val transformer = object : Transformer {


            override fun transform(builder: DynamicType.Builder<*>?, typeDescription: TypeDescription?, classLoader: ClassLoader?,
                                   module: JavaModule?): DynamicType.Builder<*> {

                return builder!!.method(ElementMatchers.any()).intercept(MethodDelegation.to(TimeInterceptor2::class
                        .java))
            }
        }


        Default()
                .type(ElementMatchers.nameStartsWith("om.sf")) // 指定需要拦截的类
                .transform(transformer)
                .with(AgentUtil.getDefaultListener())
                .installOn(inst)
    }
}

object TimeInterceptor {
    @RuntimeType
    @Throws(Exception::class)
    @JvmStatic
    fun intercept(@This thisObj: Any, @Super superObj: Any, @Origin method: Method,
                  @SuperCall callable: Callable<*>): Any {
        val start = System.currentTimeMillis()
        try {
            // 原有函数执行
            return callable.call()
        } finally {
            println(method.toString() + ": took " + (System.currentTimeMillis() - start) + "ms")
        }
    }

    @RuntimeType
    @Throws(Exception::class)
    @JvmStatic
    fun intercept(@This tobj: Any, @SuperCall callable: Callable<*>, @AllArguments obj: Array<Any>?): Any {
        val start = System.currentTimeMillis()
        try {
            // 原有函数执行
            return callable.call()
        } finally {
            println(": took " + (System.currentTimeMillis() - start) + "ms")
        }
    }

}