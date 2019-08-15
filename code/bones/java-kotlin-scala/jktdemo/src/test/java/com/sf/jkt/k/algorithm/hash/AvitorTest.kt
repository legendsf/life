package com.sf.jkt.k.algorithm.hash

import com.googlecode.aviator.AviatorEvaluator
import com.googlecode.aviator.runtime.function.AbstractFunction
import com.googlecode.aviator.runtime.function.FunctionUtils
import com.googlecode.aviator.runtime.type.AviatorBigInt
import com.googlecode.aviator.runtime.type.AviatorDouble
import com.googlecode.aviator.runtime.type.AviatorObject

fun main() {
    testAvitor()
}

fun testAvitor(){
    val result=AviatorEvaluator.execute("1+2+3")
    println(result)
    val map= mutableMapOf<String,Any>("username" to "Bob")
    println(AviatorEvaluator.execute("'hello:'+username+'!'",map))
    println(AviatorEvaluator.execute("date_to_string(sysdate(),'yyyy-MM-dd HH:mm:ss')"))
    AviatorEvaluator.addFunction(AddFunction())
    println(AviatorEvaluator.execute("myAdd(12.23,-2.3)"))
    val params= mutableMapOf<String,Any>("a" to 12.23, "b" to -2.3)
    println(AviatorEvaluator.execute("myAdd(a,b)",params))
}

class AddFunction:AbstractFunction(){
    override fun call(env: MutableMap<String, Any>?, arg1: AviatorObject?, arg2: AviatorObject?): AviatorObject {
        val num1=FunctionUtils.getNumberValue(arg1,env).toDouble()
        val num2=FunctionUtils.getNumberValue(arg2,env).toDouble()
        return AviatorDouble(num1+num2)
    }

    override fun getName(): String {
        return "myAdd"
    }
}