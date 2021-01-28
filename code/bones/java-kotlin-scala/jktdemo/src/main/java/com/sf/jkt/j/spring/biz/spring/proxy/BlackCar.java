package com.sf.jkt.j.spring.biz.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class BlackCar implements Car {
    @Override
    public String say() {
        return "BLACK_CAR";
    }
}
