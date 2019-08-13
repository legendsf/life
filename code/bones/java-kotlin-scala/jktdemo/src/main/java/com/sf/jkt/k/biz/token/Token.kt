package com.sf.jkt.k.biz.token

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Token(val save: Boolean = false, val remove: Boolean = false)

class TokenInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val method = handler.method
            val anno = method.getAnnotation(Token::class.java)
            if (anno != null) {
                //need save session
                if (anno.save) {
                    request.getSession(true).setAttribute("token", UUID.randomUUID().toString())
                }
                //need remove session
                if (anno.remove) {
                    if (isRepeatSubmit(request)) {
                        //禁止请求
                        return false
                    }
                    request.getSession(true).removeAttribute("token")
                }
            }
            //允许请求通过
            return true
        } else {
            //其他过滤器
            return super.preHandle(request, response, handler)
        }
    }


    fun isRepeatSubmit(request: HttpServletRequest): Boolean {
        val serverToken = request.getSession(false).getAttribute("token")
        //session 中没有token 禁止通行
        if (serverToken == null) {
            return true
        }
        val clientToken = request.getParameter("token")
        //client 中没有token 禁止通行
        if (clientToken == null) {
            return true
        }
        //client token 不等于 server token 禁止通行
        if (!serverToken.equals(clientToken)) {
            return true
        }
        //不为重复请求，请放行
        return false
    }

}