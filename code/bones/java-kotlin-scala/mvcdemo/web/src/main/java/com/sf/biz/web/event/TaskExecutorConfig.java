package com.sf.biz.web.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutorConfig {
    /***
     * 创建异步任务执行线程池
     * @return
     */
    @Bean("taskExecutor")
    public TaskExecutor getAsyncExecutor(Environment env) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setMaxPoolSize(Integer.valueOf(env.getProperty("spring.asyn.max.size")));
//        taskExecutor.setCorePoolSize(Integer.valueOf(env.getProperty("spring.asyn.core.size")));
//        taskExecutor.setQueueCapacity(Integer.valueOf(env.getProperty("spring.asyn.queue.size")));
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}