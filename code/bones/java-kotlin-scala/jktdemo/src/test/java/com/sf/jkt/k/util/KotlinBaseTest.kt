package com.sf.jkt.k.util

import com.alibaba.fastjson.JSON
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.Gson
import com.sf.jkt.k.Util.Log
import com.sf.jkt.k.Util.Log2
import com.sf.jkt.k.Util.Log3
import com.sf.jkt.k.Util.Log4
import com.sf.jkt.k.comp.javaagent.advice.AdviceProfiled
import com.sf.jkt.k.entity.*
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.state.ConnectionState
import org.apache.curator.framework.state.ConnectionStateListener
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.retry.RetryNTimes
import org.springframework.web.util.HtmlUtils
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.lang.StringBuilder

val ZK_ADDRESS = "127.0.0.1:2181"
val ZK_PATH = "f:/tmp/zookeeper"
fun main() {
    var v1=1L
    var v2=v1.shl(32)
    println(v1)
    println(v2)
    println(v1==v2)
    testShift()
}

fun testShift(){
    var time:Long=System.currentTimeMillis()/1000
    //[0,1,2,3]
    var idcNo=2L
    var zkNode=99L
    var seqNo=1024L
    var mod=3 % 4096

    time=time.shl(32)
    idcNo=idcNo.shl(30)
    zkNode=zkNode.shl(23)
    seqNo=seqNo.shl(12)
    println("time:"+java.lang.Long.toBinaryString(time))
    println("idcNo:"+java.lang.Long.toBinaryString(idcNo))
    println("zkNode:"+java.lang.Long.toBinaryString(zkNode))
    println("seqNo:"+java.lang.Long.toBinaryString(seqNo))
    var id= time+idcNo+zkNode+seqNo
    println("id without mod:"+java.lang.Long.toBinaryString(id))
    id=id+mod
    println("id with mod:"+java.lang.Long.toBinaryString(id))
    println(id)
    println("id.shr(12):"+java.lang.Long.toBinaryString(id.shr(12)))
    println("id.shr(12).shl(12):"+java.lang.Long.toBinaryString(id.shr(12).shl(12)))
    //getModbyOrderKey
    var rMod=id-id.shr(12).shl(12)
    println("rMod:"+rMod)
    var rIdcNo=id.shl(32).ushr(62)
    println("id.shl(32):"+ java.lang.Long.toBinaryString( id.shl(32)))
    println("id.shl(32).ushr(62):"+java.lang.Long.toBinaryString(id.shl(32).shr(62)))
    println("rIdcNo:"+rIdcNo)
}

fun test1(){
    println(Math.addExact(1,10))
//    println(Math.addExact(Int.MAX_VALUE,2))
}

fun testAgent(){
    Log.log.info("xxxxxxLog")
//    Log2.log.info("xxxxxxLog2")
//    Log3.log.info("xxxxxxLog3")
    Log4.info("hello log4")
    AdviceProfiled().profile(1000,"params")
}

fun testLog(){
    println("hello world")
}




fun testIter() {
    for (i in 0 until 10) {
        println(i)
    }
    println("*******************")
    for (i in 0..10) {
        println(i)
    }

}

fun testJson6() {
    var text = "\"text=text\""
    text="text=text"
    var mapper=ObjectMapper()
    mapper.registerKotlinModule()
    var obj: Form5 = mapper.readValue(text, Form5::class.java)
    println(obj)
    obj = JSON.parseObject(text, Form5::class.java)
    println(obj)
}


fun testJson5() {
    var obj = Form5("xxx", "yyy")
    obj = Form5(text = "xxx")
    val text = JSON.toJSONString(obj)
    println(text)
    var obj1 = JSON.parseObject(text, Form5::class.java)
    println(obj1)
    var ins = ByteArrayInputStream(text.toByteArray(Charsets.UTF_8))
    var obj2: Form5 = JSON.parseObject(ins, Form5::class.java)
    println(obj2)
}


fun testJson1() {
    val mapper = ObjectMapper()
    mapper.registerKotlinModule()
    val jform1 = Jform("yyy")
    val text1 = mapper.writeValueAsString(jform1)
    println(text1)
    val obj1 = mapper.readValue(text1, Jform::class.java)
    println(obj1)
}

fun testIterator() {
    var list = listOf<String>("a", "b", "c")
    val iterator = list.iterator()
    iterator.forEach {
        println(it.length)
    }
}

fun testByte() {

    println(1.toByte())
    println('1'.toByte())
    println(0xAF)
    println(0xAF.toByte().toInt())
    println(0xAF.toUByte())
}

fun testOutputStream(output: OutputStream) {
    output.use { }
}

fun testHighLevelFun() {
    println("aaab".lastChar())
    println(listOf("a", "b", "c").joinToString(prefix = "[", postfix = "]"))
}

fun String.lastChar(): Char = this.get(this.length - 1)

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }


fun <T> Collection<T>.joinToString(separator: String = ",", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, value) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(value)
    }
    result.append(postfix)
    return result.toString()
}

fun testParse() {
    var flag1 = Boolean::class.java.isInstance(true)
    var flag2 = Boolean::class.javaObjectType.isInstance(true)
    println(flag1)
    println(flag2)
}

fun test_null() {
    val str: String? = null
    println(str?.length)
}

fun test_gson() {
    val user = MUser(1, "name1", "pass1")
    val str = Gson().toJson(user)
    val rUser = Gson().fromJson<MUser>(str, MUser::class.java)
    println(rUser)
    val str1 = JSON.toJSONString(user)
    val rUser1 = JSON.parseObject(str, MUser::class.java)
    println(rUser1)
    println(JSON.parseObject(str, getMClass()))
    println(JSON.parseObject(str, JavaTest.getMClass()))
//    val mapper=DefaultClassMapper()
//    val target= mapper.toClass(null)
//    println(JSON.parseObject(str,target))
}

fun getMClass(): Class<*> {
    return MUser::class.java
}

fun testUser() {
    val user = MUser(1, "sfname", "sfpass")
    println(user)
    val (id, name, pass) = user
    println("" + id + ":" + name + ":" + pass)
}

fun test_zookeeper1() {
//    val server = TestingServer()
//    println(server.connectString)
//    val client = CuratorFrameworkFactory.newClient(server.connectString, ExponentialBackoffRetry(1000, 3))
//    client.connectionStateListenable.addListener(object : ConnectionStateListener {
//        override fun stateChanged(client: CuratorFramework?, newState: ConnectionState?) {
//            println("client state:" + newState?.name)
//        }
//    })
//    client.start()
}

fun test_zookeeper() {
    val client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, RetryNTimes(10, 5000))
    client.start()
    client.delete().deletingChildrenIfNeeded().forPath("/parent")
    println("zookeeper started !")
    val t = client.create().forPath("/parent")
    println(t::class)
    println(t)
    val t1 = client.create().forPath("/parent/children1", "mchild1".toByteArray())
    println(t1)

}