package com.sf.biz.web.spring.apollo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> {

    private int code;

    private String msg;

    private T data;

}