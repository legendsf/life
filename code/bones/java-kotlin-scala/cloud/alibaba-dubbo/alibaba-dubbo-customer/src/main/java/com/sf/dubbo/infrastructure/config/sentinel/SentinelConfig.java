package com.sf.dubbo.infrastructure.config.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.protocol.dubbo.DecodeableRpcResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class SentinelConfig {
    @PostConstruct
    public void init(){
        //设置降级Fallback
        setConsumerFallback();
        setProviderFallback();
        //将自定义的阈值提示加载到应用中
        //      WebCallbackManager.setUrlBlockHandler(new DemoUrlBlockHandler());
        //黑白名单
//        WebCallbackManager.setRequestOriginParser(new IpRequestOriginParser());
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

    private void setProviderFallback(){
        DubboAdapterGlobalConfig.setProviderFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                log.info("ProviderFallback.捕获到 block异常，降级处理",e);
                Object fallbackResult="服务器处理不过来了";
                return AsyncRpcResult.newDefaultAsyncResult(fallbackResult,invocation);
            }
        });
    }

    private void setConsumerFallback(){
        DubboAdapterGlobalConfig.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                log.info("ConsumerFallback.捕获到 block异常，降级处理",e);
                Object fallbackResult="服务器处理不过来了";
                return AsyncRpcResult.newDefaultAsyncResult(fallbackResult,invocation);
            }
        });
    }
}
