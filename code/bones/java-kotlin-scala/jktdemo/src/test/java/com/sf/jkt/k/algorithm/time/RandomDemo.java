package com.sf.jkt.k.algorithm.time;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * https://www.jb51.net/article/122437.htm
 * random 伪随机
 */
public class RandomDemo {
    public static void testRandom(){
        Random random = new Random(1);
        SecureRandom sr = new SecureRandom("1".getBytes(StandardCharsets.UTF_8));
        for(int i=0; i<5; i++){
            System.out.print(random.nextInt()+"\t");
        }
        System.out.println();
        for (int i=0;i<5;i++){
            System.out.print(sr.nextInt()+"\t");
        }
        System.out.println("");
    }

    @Test
    public void testThreadLocalRandom(){
        System.out.println(ThreadLocalRandom.current().nextInt(10));
        System.out.println(ThreadLocalRandom.current().nextInt(10));
        System.out.println(ThreadLocalRandom.current().nextInt(10));
    }

    @Test
    public void testRandom1(){
        testRandom();
        System.out.println("---------------------");
        testRandom();
        System.out.println("---------------------");
        testRandom();
    }

}
