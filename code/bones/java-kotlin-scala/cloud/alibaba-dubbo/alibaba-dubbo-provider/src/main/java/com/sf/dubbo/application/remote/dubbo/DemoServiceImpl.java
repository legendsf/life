package com.sf.dubbo.application.remote.dubbo;

import com.sf.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService(interfaceClass = DemoService.class)
@Component
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello() {
        return "hello";
    }
}
