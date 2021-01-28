package com.sf.jkt.k.util;

import com.sf.jkt.j.spring.biz.retry.RetryService;
import com.sf.jkt.j.spring.biz.spring.proxy.BlackCar;
import com.sf.jkt.k.util.base.ApplicationJBaseTestJ;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationTest extends ApplicationJBaseTestJ {
    @Autowired
    RetryService retryService;

    @Autowired
    BlackCar blackCar;

    @Test
    public void testRetryUnRetry(){
        retryService.test();
    }

    @Test
    public void testRetryable(){
        retryService.devide(1,0);
    }

    @Test
    public void testCar(){
        blackCar.say();
    }
}
