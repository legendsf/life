package com.sf.biz.web.spring.apollo;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    //超时结果
    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1,"超时","out of time");
 
    //超时时间
    private static final long OUT_OF_TIME = 3000L;
    @Autowired
    private TaskQueue taskQueue;
 
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public DeferredResult<ResponseMsg<String>> getResult() {
        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        StopWatch watch = StopWatch.createStarted();
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);
        result.onTimeout(() -> {
            log.info("调用超时");
        });
        result.onCompletion(() -> {
            log.info("调用完成");
        });
        //并发，加锁
        synchronized (taskQueue) {
            taskQueue.put(result);
        }
        watch.split();
        System.out.println("servlet 释放资源："+watch.getTime());
        return result;
    }
}