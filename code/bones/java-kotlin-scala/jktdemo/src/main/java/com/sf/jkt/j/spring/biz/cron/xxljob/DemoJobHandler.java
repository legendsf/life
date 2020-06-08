package com.sf.jkt.j.spring.biz.cron.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@Component
public class DemoJobHandler {
    @XxlJob("simpleJobHandler")
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("Hello-xxl-job");
        return ReturnT.SUCCESS;
    }
}
