package com.sf.jkt.k.biz.filter

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.lang.Exception
import java.lang.annotation.RetentionPolicy
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@WebFilter(urlPatterns = ["/**"], filterName = "tokenFilter")
class MyFilter() : Filter {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        log.info("我是Filter,我拦截到了请求")
        chain!!.doFilter(request, response)
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Log

@Component
class MyInterceptor() : HandlerInterceptorAdapter() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    /***
     * 在controller方法调用前执行
     * interceptA->interceptB->controllerMethod
     * 返回false 中断整个请求，后续的拦截器也不执行了
     * 返回true 则继续执行后面的拦截器
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info("MyInterceptor.preHandle")
        return super.preHandle(request, response, handler)
    }

    /****
     * controller中有异步请求方法时触发该方法
     * 先是所有的pre执行
     *
     */
    override fun afterConcurrentHandlingStarted(request: HttpServletRequest, response: HttpServletResponse, handler: Any) {
        log.info("MyInterceptor.afterConcurrentHandlingStarted")
        super.afterConcurrentHandlingStarted(request, response, handler)
    }

    /***
     * 在controller的方法执行完成后，在渲染之前执行
     */
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        log.info("MyInterceptor.postHandle")
        super.postHandle(request, response, handler, modelAndView)
    }

    /***
     * 在modeView 渲染之后执行
     */
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        log.info("MyInterceptor.afterCompletion")
        super.afterCompletion(request, response, handler, ex)
    }
}

@Component
class MyInterceptor1() : HandlerInterceptorAdapter() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    /***
     * 在controller方法调用前执行
     * interceptA->interceptB->controllerMethod
     * 返回false 中断整个请求，后续的拦截器也不执行了
     * 返回true 则继续执行后面的拦截器
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info("MyInterceptor1.preHandle")
        return super.preHandle(request, response, handler)
    }

    /****
     * controller中有异步请求方法时触发该方法
     * 先是所有的pre执行
     *
     */
    override fun afterConcurrentHandlingStarted(request: HttpServletRequest, response: HttpServletResponse, handler: Any) {
        log.info("MyInterceptor1.afterConcurrentHandlingStarted")
        super.afterConcurrentHandlingStarted(request, response, handler)
    }

    /***
     * 在controller的方法执行完成后，在渲染之前执行
     */
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        log.info("MyInterceptor1.postHandle")
        super.postHandle(request, response, handler, modelAndView)
    }

    /***
     * 在modeView 渲染之后执行
     */
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        log.info("MyInterceptor1.afterCompletion")
        super.afterCompletion(request, response, handler, ex)
    }
}