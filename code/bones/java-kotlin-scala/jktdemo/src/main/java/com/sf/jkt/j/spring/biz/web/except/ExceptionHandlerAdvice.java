package com.sf.jkt.j.spring.biz.web.except;

import com.sf.jkt.j.spring.biz.web.good.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO<String>(ResultCode.FAIL,objectError.getDefaultMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResultVO<String> ApiExceptionHandler(ApiException e){
        return  new ResultVO<String>(ResultCode.FAIL,e.getMessage());
    }
}
