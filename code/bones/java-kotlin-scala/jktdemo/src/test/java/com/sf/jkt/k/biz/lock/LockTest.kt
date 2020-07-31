package com.sf.jkt.k.biz.lock


import java.util.concurrent.TimeUnit

fun main() {
    val lt=LockTest()
    lt.testWait()
    TimeUnit.SECONDS.sleep(100)
}

class LockTest{
    var lock=Any()
    var map= mutableMapOf<String,Any>()

    fun testWait(){
        synchronized(lock){
            map["miller"]=Any()
            map["mike"]=Any()
            map.remove("mike")
            map.remove("miller")

        }
    }

    fun testSleep(){
        synchronized(lock){
            map["miller"]=Any()
            map["mike"]=Any()
            TimeUnit.SECONDS.sleep(5)
            map.remove("mike")
            map.remove("miller")

        }
    }

}

