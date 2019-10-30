package com.sf.jkt.k.biz.annotation

import java.util.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Value(val value:String)

class AnnotationExpression(val obj:Any){
    fun expression(){
        val clazz=obj::class
        clazz.declaredMemberProperties.forEach { prop->
            val mutableProp=try{
                prop as KMutableProperty<*>
            }catch (e:Exception){
                null
            }?:return@forEach
            mutableProp.annotations.forEach { annotation->
                val propClassName=mutableProp.returnType.toString().removePrefix("kotlin.")
                when(propClassName){
//                    in numtypeSet->mutableProp.setter.call(obj,
//                            (readProp(annotation as Value) as kotlin.String).toNum(propClassName))
                    "String"->mutableProp.setter.call(obj,
                            (readProp(annotation as Value) as kotlin.String))
                    "Boolean"->mutableProp.setter.call(obj,
                            (readProp(annotation as Value) as kotlin.String).toBoolean())

                }
            }
        }
    }


    private fun readProp(value:Value): Any? {
        val prop= Properties()
        prop.load( AnnotationExpression::class.java.getResource("app.properties").openStream())
        return prop.get(value.value)
    }

}

//fun String.toNum(className:String):Any{
////    val clazz=Class.forName("java.lang.${typeMap[className]}")
//}