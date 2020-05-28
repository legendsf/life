package com.sf.jkt.j.spring.biz.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.jodah.expiringmap.ExpiringMap;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/***
 * guava cache 使用
 * https://www.jianshu.com/p/c8532617773e
 * 最好用 spring cache
 * https://www.cnblogs.com/woshimrf/p/5923501.html
 *
 * https://www.cnblogs.com/stoneFang/p/11674388.html
 */
public class CacheTest {
    private Cache<String, Serializable> lockCache =
            CacheBuilder.newBuilder().maximumSize(500).expireAfterWrite(3, TimeUnit.SECONDS).build();

    private ExpiringMap<String, Serializable> localCache = ExpiringMap.builder().variableExpiration()
            .build();
    public static void main(String[] args) {
    }
}
