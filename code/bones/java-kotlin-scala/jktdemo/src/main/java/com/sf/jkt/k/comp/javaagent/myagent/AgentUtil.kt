package com.sf.jkt.k.comp.javaagent.myagent

import cn.hutool.log.StaticLog
import net.bytebuddy.ByteBuddy
import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.description.NamedElement
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.dynamic.scaffold.TypeValidation
import net.bytebuddy.matcher.ElementMatchers
import net.bytebuddy.matcher.ElementMatchers.nameContains
import net.bytebuddy.matcher.ElementMatchers.nameStartsWith
import net.bytebuddy.utility.JavaModule
import org.slf4j.LoggerFactory

object AgentUtil {
    private val log = LoggerFactory.getLogger(this.javaClass)
    fun getDefaultListener(): AgentBuilder.Listener {
        return object : AgentBuilder.Listener {
            override fun onComplete(typeName: String?, classLoader: ClassLoader?, module: JavaModule?, loaded: Boolean) {
            }

            override fun onDiscovery(typeName: String?, classLoader: ClassLoader?, module: JavaModule?, loaded: Boolean) {
            }

            override fun onIgnored(typeDescription: TypeDescription?, classLoader: ClassLoader?, module: JavaModule?, loaded: Boolean) {
            }

            override fun onTransformation(typeDescription: TypeDescription?, classLoader: ClassLoader?, module: JavaModule?,
                                          loaded: Boolean, dynamicType: DynamicType?) {
                if (log.isDebugEnabled) {
                    log.debug("On Transformation Class {}.", typeDescription!!.getName())
                }
            }

            override fun onError(typeName: String?, classLoader: ClassLoader?, module: JavaModule?, loaded: Boolean, throwable: Throwable?) {
                log.error("Enhance Class " + typeName + "error.", throwable)
            }
        }
    }

    fun createBuilder(): AgentBuilder {
        val byteBuddy = ByteBuddy()
                .with(TypeValidation.of(true))

        val agentBuilder = AgentBuilder.Default(byteBuddy)
                .ignore(
                        nameStartsWith<NamedElement>("net.bytebuddy.")
                                .or<NamedElement>(nameStartsWith<NamedElement>("org.slf4j."))
                                .or<NamedElement>(nameStartsWith<NamedElement>("org.apache.logging."))
                                .or<NamedElement>(nameStartsWith<NamedElement>("org.groovy."))
                                .or<NamedElement>(nameContains<NamedElement>("javassist"))
                                .or<NamedElement>(nameContains<NamedElement>(".asm."))
                                .or<NamedElement>(nameStartsWith<NamedElement>("sun.reflect"))
                                .or<NamedElement>(nameStartsWith<NamedElement>("java."))
                                .or(ElementMatchers.isSynthetic())
                )
        return agentBuilder
    }
}
