package com.sf.biz.web.spring.aop.example;


import org.springframework.stereotype.Component;

@A1
@Component
public class Human {
    public void say(String sentence)
    {
        System.out.println("Human says:" + sentence);
    }

    public void run()
    {
        System.out.println("Human runs." );
    }

    public void jump()
    {
        System.out.println("Human jump." );
    }

}
