package com.sf.jkt.j.spring.biz.dto.entity;

import java.util.List;

public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}