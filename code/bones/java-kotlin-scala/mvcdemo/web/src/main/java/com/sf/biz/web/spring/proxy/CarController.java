package com.sf.biz.web.spring.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired(required = false)
    RedCar redCar;
    @Autowired
    BlackCar blackCar;

    @RequestMapping("/getBlackCar")
    public String getBlackCar(){
        return "BlackCar:"+blackCar.say();
    }
}
