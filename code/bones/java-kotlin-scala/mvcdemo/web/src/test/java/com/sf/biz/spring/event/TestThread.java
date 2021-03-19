package com.sf.biz.spring.event;

import com.sf.base.ApplicationBaseTest;
import com.sf.biz.web.event.ContentEvent;
import com.sf.biz.web.event.ScanData;
import com.sf.biz.web.spring.aop.self.MySelfService;
import com.sf.biz.web.spring.aop.self.Myhello1;
import com.sf.biz.web.spring.aop.self.Myhello1impl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

public class TestThread extends ApplicationBaseTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Myhello1 myhello1impl;

    @Autowired
    private MySelfService mySelfService;

    @Test
    public void testAspect()throws Exception{
        myhello1impl.hello1();
        System.out.println("-------------------");
        myhello1impl.hello2();
        System.out.println("-------------------");
        mySelfService.fun1();
        System.out.println("-------------------");
        mySelfService.fun2();
        mySelfService.fun2();
    }

    @Test
    public void handler(){
        Long start=System.currentTimeMillis();
        for(int i=1;i<100;i++){
            ScanData a = new ScanData();
            a.setProductId(i);
            a.setProductName("aa"+i);
            ContentEvent event = new ContentEvent("aa", a);
            applicationContext.publishEvent(event);
        }
        Long end=System.currentTimeMillis();
        System.out.println("处理完毕："+(end-start));
    }

}