package com.sf.jkt.k.util

fun main() {
    testRegex()
}

fun testRegex(){
    var regex="(http://[^/]+)(/\\S*)".toRegex()
    regex.matchEntire("http://www.ibeifeng.com/user.php?act=mycourse")?.groupValues?.forEach { println(it) }
    println(regex.matchEntire("http://www.ibeifeng.com/user.php?act=mycourse")?.groupValues?.get(1))

}
