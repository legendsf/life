package com.sf.jkt.k.util

import org.springframework.core.io.ClassPathResource


fun main() {
    var pa= cn.hutool.core.io.resource.ClassPathResource("movie/user.data").absolutePath
    println(pa)
    var pa1= ClassPathResource("movie/user.data")
    println(pa1.file.exists())

}

fun testRegex(){
    var regex="(http://[^/]+)(/\\S*)".toRegex()
    regex.matchEntire("http://www.ibeifeng.com/user.php?act=mycourse")?.groupValues?.forEach { println(it) }
    println(regex.matchEntire("http://www.ibeifeng.com/user.php?act=mycourse")?.groupValues?.get(1))

}
