package com.sf.jkt.k.algorithm.hash

import com.google.common.hash.Hashing
import com.sf.jkt.k.entity.MUser

fun main() {
    //userid
    var id = 1000L
    var machineNumbers = 27
    var bucket = Hashing.consistentHash(id, machineNumbers)
    println("应当存放的机器号为:" + bucket)
    var user=MUser(188,"sfname","sfpass")
    println(user.hashCode().javaClass)
    var bucket1=Hashing.consistentHash(user.hashCode().toLong(),machineNumbers)
    println("user应该存放的机器为:"+bucket1)
    println(Hashing.md5())
}

