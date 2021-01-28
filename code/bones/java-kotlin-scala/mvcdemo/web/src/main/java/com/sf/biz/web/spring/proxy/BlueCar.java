package com.sf.biz.web.spring.proxy;

public class BlueCar implements Car{
   private String  carName;


    @Override
    public String say() {
        return "BLUE_CAR";
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarName() {
        return carName;
    }
}
