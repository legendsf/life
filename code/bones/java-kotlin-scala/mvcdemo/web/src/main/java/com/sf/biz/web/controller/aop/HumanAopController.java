package com.sf.biz.web.controller.aop;

import com.sf.biz.web.spring.aop.example.Boy;
import com.sf.biz.web.spring.aop.example.Human;
import com.sf.biz.web.spring.aop.example.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/human")
public class HumanAopController {
    @Autowired
    Human human;
    @Autowired
    Boy boy;
    @Autowired
    Man man;

    @RequestMapping("/hello")
    public void human(){
        System.out.println("---------------------This is a Human.");

        human.say("hello!");
        human.jump();
        human.run();

        System.out.println("---------------------This is a Man.");

        man.say("hello!");
        man.jump();
        man.run();

        System.out.println("---------------------This is a Boy.");
        boy.say("hello!");
        boy.jump();

    }
}
