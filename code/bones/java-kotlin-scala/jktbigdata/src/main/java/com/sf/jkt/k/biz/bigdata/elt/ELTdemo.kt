package com.sf.jkt.k.biz.bigdata.elt

import org.apache.hadoop.hive.ql.exec.UDF
import org.apache.hadoop.io.Text

/**
 * hive etl practice
 * https://www.jianshu.com/p/da1f9bc326ee
 **/

/**
 *
 * 从 "GET /course/view.php?id=27 HTTP/1.1" 中获取请求地址，即 /course/view.php?id=27
 *
 */
class TruncationRequestAdd: UDF() {
    fun evaluate(add: Text):Text?{
        if(add==null){
            return null
        }
        val strs=add.toString().split(" ")
        if(strs.size<3){
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
class TruncationMainAdd:UDF(){
   fun evaluate(add:Text):Text?{
        if(add==null){
            return null
        }
       var address=add.toString()
       if(!address.startsWith("http://")){
           return null
       }
       var regex="(http://[^/]+)(/\\S*)"
       return null
   }
}