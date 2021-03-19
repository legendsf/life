package com.sf.jkt.k.comp.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class JolDemo {
    public static void main(String[] args) throws Exception{
//        testLayout1();
        testObjectHeader();
        TimeUnit.SECONDS.sleep(10000);
    }
    public static final void testObjectHeader(){
        Object obj= new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    private static void testLayout1() {
        Object obj=  generate();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(GraphLayout.parseInstance(obj).toPrintable());
        System.out.println(GraphLayout.parseInstance(obj).totalSize());
    }

    static Object generate(){
        Map<String, Object> map = new HashMap<>(16);
        IntStream.range(1,10).forEach(i->{
            map.put(String.valueOf(i),String.valueOf(i));
        });
        return map;
    }
}
