package com.sf.jkt.k.comp.store.redis

import redis.clients.jedis.Jedis
import redis.clients.jedis.params.SetParams
import java.util.*

//只适合单点，因为主从，如果主上加锁，未同步至slave,slave切master时就能出现两个线程都获得锁
//redis 的复制是异步的

class RedisLockTest {
    companion object {
        fun tryLock(jedis: Jedis, lockKey: String, requestId: String, expireTime: Int): Boolean {
            val result = jedis.set(lockKey, requestId, SetParams.setParams().nx().px(expireTime.toLong()))
            if ("ok".equals(result)) {
                return true
            }
            return false
        }

        fun releaseLock(jedis: Jedis, lockKey: String, requestId: String): Boolean {
            val script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end"
            val result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId))
            if (1 == result) {
                return true
            }
            return false
        }
    }
}