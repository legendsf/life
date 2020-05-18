package com.sf.jkt.j.jdk8.stream;

public interface  Formula {
    double calculate(int a);
    default  double sqrt(int a){
        double d= Math.sqrt(a);
        System.out.println(d);
        return d;
    }
}
