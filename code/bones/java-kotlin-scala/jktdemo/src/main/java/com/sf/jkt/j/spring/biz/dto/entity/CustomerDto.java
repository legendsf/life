package com.sf.jkt.j.spring.biz.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}