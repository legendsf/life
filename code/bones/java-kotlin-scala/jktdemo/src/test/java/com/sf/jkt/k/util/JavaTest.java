package com.sf.jkt.k.util;

import com.sf.jkt.k.Util.Log;
import com.sf.jkt.k.Util.Log4;
import com.sf.jkt.k.comp.javaagent.advice.AdviceProfiled;
import com.sf.jkt.k.entity.MUser;
import org.apache.kafka.common.utils.Java;

import java.util.ArrayList;
import java.util.Collection;

public class JavaTest {
    static Class<?> getMClass() {
        return MUser.class;
    }

    public static void main(String[] args) {
        Long v1=1L;
        Long v2=v1 << 32;
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v1==v2);
        System.out.println(Long.toBinaryString(v1));
        System.out.println(Long.toBinaryString(v2));
        KotlinBaseTestKt.testShift();
    }



    public void testProfile(){
        Log4.info("hello");
        testOutput("hello","world");
        new AdviceProfiled().profile(1000,"params");
    }

    public void testOuput1(){
        System.out.println("hello testOutput1");
    }

    public static void testOutput(String param1,String param2){
        System.out.println("hello:"+param1+"|"+param2+"|world!");
    }

    public static void testKJMerged(){
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        System.out.println(KotlinBaseTestKt.joinToString(collection, ",", "[", "]"));
    }
}
