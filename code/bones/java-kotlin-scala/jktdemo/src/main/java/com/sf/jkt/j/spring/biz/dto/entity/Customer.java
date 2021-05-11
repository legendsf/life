package com.sf.jkt.j.spring.biz.dto.entity;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private Collection<OrderItem> orderItems;

    //getter setter省略   
}