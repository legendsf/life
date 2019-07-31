package com.sf.jkt.k.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/rest")
@RestController
class HelloController{
   @GetMapping("/hello")
   fun hello():String{
      return "hello compile1 reload world!"
   }
}
