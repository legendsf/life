package com.sf.hoxton.dto;


public class ResultVo<T> {
    public static ResultVo SUCCESS= new ResultVo("SOCCER0000","成功");
    public static ResultVo FAIL = new ResultVo("SOCCER9999","失败");
    public String code;
    public String message;
    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(T data,String code,String message){
        this.data=data;
        this.code=code;
        this.message=message;
    }
    public  boolean isSuccess(){
        return "SOCCER0000".equalsIgnoreCase(this.code);
    }

    public  boolean isFail(){
        return "SOCCER9999".equalsIgnoreCase(this.code);
    }
}
