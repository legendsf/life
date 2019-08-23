package com.sf.jkt.k.web

import com.sf.jkt.k.Util.Log
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseBody
    //https://www.cnblogs.com/jpfss/p/8478644.html
    fun exceptionHandler(e: Exception): Map<String, Any> {
        Log.log.error("GlobalExceptionHandler系统错误", e)
        return mapOf("errorCode" to "101", "errorMsg" to "系统错误!" + ExceptionUtils.getStackTrace(e))
    }
}