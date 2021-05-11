package com.sf.jkt.j.spring.biz.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {

    public String name;
    public Long quantity;
}