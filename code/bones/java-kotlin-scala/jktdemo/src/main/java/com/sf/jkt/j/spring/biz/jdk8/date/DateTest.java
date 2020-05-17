package com.sf.jkt.j.spring.biz.jdk8.date;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class DateTest {
    @Test
    public void testDuration()throws Exception{
        Instant instant = Instant.now();
        TimeUnit.SECONDS.sleep(2);
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant,instant1);
        System.out.println(duration.toDays());
       Duration d2 = duration.plusDays(3);
        System.out.println(duration.toDays());
        System.out.println(d2.toDays());
    }
}
