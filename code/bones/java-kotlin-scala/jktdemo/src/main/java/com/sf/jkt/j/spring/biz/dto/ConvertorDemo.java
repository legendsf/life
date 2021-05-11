package com.sf.jkt.j.spring.biz.dto;

import com.sf.jkt.j.spring.biz.dto.entity.Customer;
import com.sf.jkt.j.spring.biz.dto.entity.CustomerDto;
import com.sf.jkt.j.spring.biz.dto.entity.OrderItemDto;
import com.sf.jkt.j.spring.biz.dto.mapper.CustomerMapper;

import java.util.Arrays;

public class ConvertorDemo {
    public static void main(String[] args) {
        OrderItemDto orderItemDto = new OrderItemDto("fruit",2L);
        CustomerDto customerDto= new CustomerDto(1L,"songfei", Arrays.asList(orderItemDto));
        Customer customer= CustomerMapper.MAPPER.toCustomer(customerDto);
        System.out.println(customer);
    }
}
