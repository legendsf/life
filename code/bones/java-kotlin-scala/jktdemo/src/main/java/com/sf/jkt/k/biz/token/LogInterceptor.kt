package com.sf.jkt.k.biz.token

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
@Component
@Qualifier("logInterceptor")
class LogInterceptor:HandlerInterceptorAdapter() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info("请求路径:{}", request.requestURI)
        return true
    }
}