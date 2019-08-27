package com.sf.jkt.k.biz.bigdata.grafana

import cn.hutool.core.util.RandomUtil
import cn.hutool.db.Db
import cn.hutool.db.Entity
import cn.hutool.db.ds.simple.SimpleDataSource
import org.joda.time.DateTime
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit


fun main() {
    test_api_log()
}

fun test_api_log() {
    val db = Db.use("mysql")
    for (i in 0..10000) {
        val place = when {
            i % 3 == 0 -> "机房3"
            i % 5 == 0 -> "机房5"
            i % 7 == 0 -> "机房5"
            i % 11 == 0 -> "机房5"
            else -> "机房6"
        }
        val result= if(RandomUtil.randomBoolean()) "请求成功" else "请求失败"
        val entity = Entity.create("api_log").set("place", place).set("apiname", "获取用户认证状态")
                .set("requestTime", LocalDateTime.now().toString()).set("requestResult", result)
        db.insert(entity)
        TimeUnit.SECONDS.sleep(1)
    }
}

fun testItemTotal() {

    val db = Db.use("mysql")
    for (i in 0..100000) {
        val entity = Entity.create("item_total").set("time", LocalDateTime.now().toString()).set("total",
                i + RandomUtil.randomInt(100))
        db.insert(entity)
        TimeUnit.SECONDS.sleep(1)
    }
    println(db)
}