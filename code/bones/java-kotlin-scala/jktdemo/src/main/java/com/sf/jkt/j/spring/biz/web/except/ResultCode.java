package com.sf.jkt.j.spring.biz.web.except;

import lombok.Getter;

@Getter
public enum  ResultCode {
   SUCCESS(1000,"SUCCESS"),
   FAIL(1001,"ERROR");
   private int code;
   private String message;

         ResultCode(int code,String message){
            this.code=code;
            this.message=message;
         }

}
