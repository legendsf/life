package com.sf.jkt.j.spring.biz.spring.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    RedCar redCar;
    @Autowired
    BlackCar blackCar;

    @RequestMapping("/getCar")
    public String getCar(){
        return "RedCar:"+redCar.say()+"|BlackCar:"+blackCar.say();
    }
}
