package com.sf.jkt.k.comp.connection.netty.proxy;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyPool {
    public static Map<String, Channel>  source=new ConcurrentHashMap<>();
    public static  Map<String, String> proxy = new ConcurrentHashMap<>();
}
