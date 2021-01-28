package com.sf.jkt.j.spring.biz.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class RedCar implements Car{
    @Override
    public String say() {
        return "RED_CAR";
    }
}
