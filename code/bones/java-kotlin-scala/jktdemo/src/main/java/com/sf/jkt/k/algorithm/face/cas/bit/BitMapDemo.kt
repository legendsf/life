package com.sf.jkt.k.algorithm.face.cas.bit

import cn.hutool.bloomfilter.bitMap.BitMap
import cn.hutool.bloomfilter.bitMap.LongMap
import com.google.common.hash.BloomFilter
import com.google.common.hash.Funnels

fun main() {
    testBoomFilter()
}

fun testBoomFilter(){
    var filter=BloomFilter.create(Funnels.longFunnel(),10000,0.001);
    filter.put(5000)
    filter.put(6000)
    println(filter.mightContain(5000))
    println(filter.mightContain(6000))
}

fun testBitMap(){
    var bm=LongMap(10000);
    bm.add(5000);
    println(bm.contains(5000))
}