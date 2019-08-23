package com.sf.jkt.k.web

import com.sf.jkt.k.biz.token.Token
import com.sf.jkt.k.entity.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Controller
class FreemarkerController {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @GetMapping("/")
    fun welcome(model: MutableMap<String, Any>): String {
        model["time"] = Date()
        model["message"] = "yes this is message"
        return "welcome"
    }

    @GetMapping("/index")
    fun index(model: Model): String {
        model.addAttribute("message", "hello world !")
        return "index"
    }

    @GetMapping("/admin/promptBuy")
    @Token(save = true)
    fun promptBuy(model: MutableMap<String, Any>): String {
        model["time"] = Date()
        model["message"] = "yes this is message"
        return "promptBuy"
    }

    @PostMapping("/admin/saveOrder")
    @Token(remove = true)
    fun saveOrder(@RequestBody form: Form5, model: MutableMap<String, Any>): String {
        log.info("*************form:" + form)
        return "index"
    }


}