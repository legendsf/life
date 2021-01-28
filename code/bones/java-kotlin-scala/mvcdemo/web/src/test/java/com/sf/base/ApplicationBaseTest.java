package com.sf.base;


import com.sf.Application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest(classes = Application .class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationBaseTest {
    @Resource
    Environment environment;

    @Test
    public void testProfile(){
        String[] profile = environment.getActiveProfiles();
        System.out.println("********************");
        Arrays.stream(profile).forEach(System.out::println);
    }







}
