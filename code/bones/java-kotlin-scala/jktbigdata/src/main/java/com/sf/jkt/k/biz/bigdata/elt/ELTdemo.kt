package com.sf.jkt.k.biz.bigdata.elt

import org.apache.hadoop.hive.ql.exec.UDF
import org.apache.hadoop.io.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
    create temporary function rm as "com.sf.jkt.k.biz.bigdata.elt.RemoveQuote";
    create temporary function ct as "com.sf.jkt.k.biz.bigdata.elt.TruncationRequestAdd";
    create temporary function rf as "com.sf.jkt.k.biz.bigdata.elt.TruncationMainAdd";
    create temporary function df as "com.sf.jkt.k.biz.bigdata.elt.DateTransform";
 * hive etl practice
 * https://www.jianshu.com/p/da1f9bc326ee
 **/

/**
 *
 * 从 "GET /course/view.php?id=27 HTTP/1.1" 中获取请求地址，即 /course/view.php?id=27
 *
 */
class TruncationRequestAdd : UDF() {
    fun evaluate(add: Text): Text? {
        if (add == null) {
            return null
        }
        val strs = add.toString().split(" ")
        if (strs.size < 3) {
            return null
        }
        return Text(strs[1])
    }
}

/**
 *
 * 从"http://www.ibeifeng.com/user.php?act=mycourse"提取主地址，即"http://www.ibeifeng.com"
 *
 */
class TruncationMainAdd : UDF() {
    fun evaluate(add: Text): Text? {
        if (add == null) {
            return null
        }
        var address = add.toString()
        if (!address.startsWith("http://")) {
            return null
        }
        var regex = "(http://[^/]+)(/\\S*)".toRegex()
        var requestPath = regex.find(address)?.groupValues?.get(1)
        return Text(requestPath)
    }
}

/**
 * 转换日期格式
把日期格式为： "31/Aug/2015:00:04:37 +0800"
转换为： "2015-08-31 00:04:37"
 */

class DateTransform : UDF() {
    var inputFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH)
    var outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    fun evaluate(input: Text): Text? {
        if (input == null || input.toString() == null) {
            return null
        }
        val outstr = LocalDateTime.parse(input.toString(), inputFormat).format(outputFormat)
        return Text(outstr)
    }
}

/*
 * 去除字符串中的双引号
 */

open class RemoveQuote : UDF() {
    fun evaluate(input: Text): Text? {
        if (input == null || input.toString() == null) {
            return null
        }
        return Text(input.toString().replace("\"", ""))
    }
}


fun main() {
    testUDF()
}

fun testUDF(){
    var rf=RemoveQuote();
    var str="\"hello\""
    var result= rf.evaluate(Text(str))
    println("source:"+str+"|target:"+result)
    println(RemoveQuote2().evaluate(Text(str)))
}
