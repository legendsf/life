package com.sf.jkt.j.spring.biz.web.except;

import com.google.gson.Gson;
import com.sf.jkt.j.spring.biz.web.good.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 包装错误
 */
@RestControllerAdvice(basePackages = "com.sf.jkt.j.spring.biz.exception.bad")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //如果是 resultVO 直接过滤
       return ! methodParameter.getParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(methodParameter.getParameterType().equals(String.class)){
            try {
                new Gson().toJson(new ResultVO<>(o));
            } catch (Exception e) {
                throw new ApiException(1000,e.getMessage());
            }
        }
        return new Gson().toJson( new ResultVO<>(o));
    }
}
