package com.sf.jkt.k.mxbean

import com.google.gson.Gson
import java.lang.management.ManagementFactory

fun main() {
    val mxBean=ManagementFactory.getRuntimeMXBean()
    println(Gson().toJson(mxBean))
    val name=mxBean.name
    println(name)
    println(mxBean.classPath)
    println(mxBean.bootClassPath)
    println(mxBean.libraryPath)
    println(mxBean.inputArguments)
    println(mxBean.specName)
    println(mxBean.specVendor)
    println(mxBean.specVersion)


}

