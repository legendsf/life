package com.sf.jkt.j.spring.biz.web.good;

import com.sf.jkt.j.spring.biz.web.except.ResultCode;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel
public class ResultVO<T> {
    private int code;
    private String message;
    private T data;

    public ResultVO(T data) {
        this.data = data;
        code= ResultCode.SUCCESS.getCode();
        message=ResultCode.SUCCESS.getMessage();
    }

    public ResultVO(ResultCode code,T data){
       this.code=code.getCode();
       this.message=code.getMessage();
       this.data=data;
    }

}
