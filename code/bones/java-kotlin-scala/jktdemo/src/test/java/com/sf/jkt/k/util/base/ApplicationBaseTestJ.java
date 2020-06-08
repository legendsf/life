package com.sf.jkt.k.util.base;


import com.sf.jkt.j.spring.biz.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationBaseTestJ {
    @Resource
    Environment environment;

    @Test
    public void testProfile(){
        String[] profile = environment.getActiveProfiles();
        System.out.println("********************");
        Arrays.stream(profile).forEach(System.out::println);
    }







}
