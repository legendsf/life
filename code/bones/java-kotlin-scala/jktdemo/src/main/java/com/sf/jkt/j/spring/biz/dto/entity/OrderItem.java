package com.sf.jkt.j.spring.biz.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {

    private String name;
    private Long quantity;

   //getter setter省略
}