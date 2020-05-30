package com.sf.jkt.k.util;

import com.google.common.collect.Lists;
import com.sf.jkt.k.Util.Log4;
import com.sf.jkt.k.comp.javaagent.advice.AdviceProfiled;
import com.sf.jkt.k.entity.MUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaTest {
    static Class<?> getMClass() {
        return MUser.class;
    }

    public static void main(String[] args)throws Exception {

//        new JavaTest().testResource();
        testStream();
    }

    public  void testResource(){
        URL u1= JavaTest.class.getResource("file1.txt");
        System.out.println(u1);

    }

    public static void testShift(){
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

    public static void testStream()throws Exception{
        //JDK1.8
        //guava
        List<Integer> list = Lists.newArrayList(1,2,3,5,6);

        list.stream().forEach(System.out::println);
        System.out.println("***********");
        //原来的
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
