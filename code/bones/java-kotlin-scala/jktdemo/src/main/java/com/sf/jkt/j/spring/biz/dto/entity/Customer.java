package com.sf.jkt.j.spring.biz.dto.entity;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.Collection;

public class Customer {

    private Long id;
    private String name;
    private Collection<OrderItem> orderItems;

    //getter setter省略   
}