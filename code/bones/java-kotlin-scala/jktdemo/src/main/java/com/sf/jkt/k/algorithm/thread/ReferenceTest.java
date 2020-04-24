package com.sf.jkt.k.algorithm.thread;

import cn.hutool.bloomfilter.bitMap.BitMap;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceTest {

    private Map<String, SoftReference<BitMap>> imageCache = new HashMap<String, SoftReference<BitMap>>();

    public static void main(String[] args) {
        testReference();
    }

    public static void testReference() {
        //强引用
        Object obj = new Object();
        Object[] objects = new Object[1000];
        obj = null;
        objects = null;
        //软引用
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        //内存不足会回收sr,JVM sr.get() 返回 null;
        System.out.println(sr.get());
        //弱引用
        WeakReference<String> wr = new WeakReference<>(new String("hello"));
        System.out.println(wr.get());
        System.gc();
        //gc 回收之后,wr.get() 返回null
        System.out.println(wr.get());


    }

    public void addBitMapToCache(String path) {
//        BitMap bitMap = BitmapFactory.decodeFile(path);
//        SoftReference<BitMap> sb = new SoftReference<BitMap>(bitMap);
//        imageCache.put(path, sb);
    }

    public BitMap getBitMapByPath(String path) {
        SoftReference<BitMap> sb = imageCache.get(path);
        if (sb == null) {
            return null;
        }

        //如果被回收sb.get() 为null;
        BitMap bitMap = sb.get();
        return bitMap;
    }
}


