package com.sf.jkt.k.algorithm.regex

//hive elt
// https://www.jianshu.com/p/da1f9bc326ee

fun main() {
   testRegex3()
}

fun testRegex3(){
   var str="""
       "27.38.5.159" "-" "31/Aug/2015:00:04:37 +0800" "GET /course/view.php?id=27 HTTP/1.1" "303" "440" - "http://www.ibeifeng.com/user.php?act=mycourse" "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36" "-" "learn.ibeifeng.com"
   """.trimIndent()
   var regex="""
       (\"[^ ]*\") (\"[-|^ ]*\") (\"[^\"]*\") (\"[^\"]*\") (\"[0-9]*\") (\"[0-9]*\") ([-|^ ]*) (\"[^ ]*\") (\"[^\"]*\") (\"[-|^ ]*\") (\"[^ ]*\")
   """.trimIndent().toRegex()
    println(regex.matches(str))
    println(regex.matchEntire(str)?.groupValues?.size)
   println(regex.matchEntire(str)?.groupValues?.forEach { println(it) })
}

fun testRegex2(){
   var str="\"31/Aug/2015:00:04:37 +0800\""
   var regex="\"[^\"]+\"".toRegex()
   println(regex.findAll(str)?.forEach { println(it.value) })
   println(regex.matches(str))
   println(regex.containsMatchIn(str))
   println(regex.matchEntire(str))
}

fun testRegex(){


   var str="\"GET /course/view.php?id=27 HTTP/1.1\""
//   str="\"abc\""
//   str="a a"
   var regexstr="(\".+\") "
   regexstr="(\".+\")"
   regexstr="(\\w|-| |.)+"
   val regex=Regex(regexstr,RegexOption.IGNORE_CASE)
   val result=regex.matchEntire(str)?.groupValues
    result?.forEach { println(it) }


}

fun testRegex1(){
   var r1=Regex("[a-z]+",RegexOption.IGNORE_CASE)
   println(r1.matches("Abcdef"))
   println(r1.matches("ABC"))
   println(r1.matchEntire("abcD")?.groupValues)
   println("[^a|^ ]*".toRegex().matches("bc27.38.5.159\\"))
   println(Regex("[0-9]+").containsMatchIn("012abc"))
   println(Regex("[0-9]+").matchEntire("123456")?.value)
   println(Regex("[0-9]+").matchEntire("123456")?.groupValues?.size)
   println(Regex("[0-9]+").matchEntire("123456")?.groupValues)
   println(Regex("[0-9]+").replace("123 world","hello"))
   println(Regex("[0-9]+").find("123hello456")?.value)
   println(Regex("[0-9]+").findAll("123hello456")?.forEach { println(""+it.value+" "+it.range) })
   val str1="123bb456dd789aa"
   val regex1="([0-9]+)([a-z]+)([0-9]+)([a-z]+)([0-9]+)([a-z]+)".toRegex()
   regex1.findAll(str1)?.forEach { println("begin"+it.value) }
   val result2=regex1.matchEntire(str1)?.groupValues?.forEach { println(it) }
}
