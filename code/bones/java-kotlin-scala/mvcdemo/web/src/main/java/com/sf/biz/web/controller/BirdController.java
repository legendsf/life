package com.sf.biz.web.controller;

import com.sf.biz.web.entity.Bird;
import com.sf.biz.web.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bird")
public class BirdController {
    @Autowired
    BirdService birdService;

    @RequestMapping("/byId")
    public Bird getBirdById(@RequestParam("id") int id){
        return birdService.selectBirdById(id);
    }
}
