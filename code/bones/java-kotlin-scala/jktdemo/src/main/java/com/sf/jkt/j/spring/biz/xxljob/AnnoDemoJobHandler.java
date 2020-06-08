package com.sf.jkt.j.spring.biz.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AnnoDemoJobHandler {
    @XxlJob("jobDemo")
    public ReturnT<String> jobDemo(String s)throws Exception{
        XxlJobLogger.log("XXLJOB-HELLO-WORLD");
        TimeUnit.SECONDS.sleep(2);
        return ReturnT.SUCCESS;
    }
}
