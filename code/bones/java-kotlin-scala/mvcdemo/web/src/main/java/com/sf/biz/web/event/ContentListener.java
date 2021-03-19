package com.sf.biz.web.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @desc 监听事件
 * @author  yule
 * @create  2019/1/25
 **/
@Component
public class ContentListener implements ApplicationListener<ContentEvent> {
    @Async
    @Override
    public void onApplicationEvent(ContentEvent contentEvent) {
        ScanData a = contentEvent.getData();
        int b=0;
        for(int i=0;i<10000;i++){
            b=i+b;
        }
        System.out.println(Thread.currentThread().getName()+a.toString());
    }
}