package com.sf.jkt.k.web

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

//@RequestMapping("/rest")
//@RestController 不支持静态资源
@RestController
class HelloRestController {




    @GetMapping("/hello")
    fun hello(): String {
        return "hello compile1 reload world!"
    }


}
