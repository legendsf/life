package com.sf.jkt.k.algorithm.face.cas

import redis.clients.jedis.Jedis
import redis.clients.jedis.params.SetParams
import java.util.*


fun main() {
    testRedisLock()
}

fun testRedisLock(){
    val jedis=Jedis("127.0.0.1",6379)
    val result=RedisLock.lock(jedis,"uk","uv",1000L)
    println("get redis lock:"+result)
    val result2=RedisLock.lock(jedis,"uk","uv",1000L)
    println("get redis lock2:"+result2)
    val result3=RedisLock.unlock(jedis,"uk","uv")
    println("release redis lock:"+result3)
}

 class ZkLock{

 }

class RedisLock{
    companion object{
        fun lock(jedis:Jedis,lockkey:String,lockValue:String,expire:Long):Boolean{
            val result=jedis.set(lockkey,lockValue, SetParams().nx().px(expire))
            if("ok".equals(result,true)){
                return true
            }
            return false
        }

        fun unlock(jedis: Jedis,lockkey: String,lockValue: String):Boolean{
            val script="if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end"
            val result=jedis.eval(script, Collections.singletonList(lockkey),Collections.singletonList(lockValue))
            if((result as Long)==1L){
                return true
            }
            return false
        }

    }

}
