package com.sf.biz.web.spring.proxy;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
public class RedCar implements Car{
    @Override
    public String say() {
        return "RED_CAR";
    }
}
