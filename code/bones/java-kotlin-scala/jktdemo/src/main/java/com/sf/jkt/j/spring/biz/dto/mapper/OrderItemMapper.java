package com.sf.jkt.j.spring.biz.dto.mapper;

import com.sf.jkt.j.spring.biz.dto.entity.OrderItem;
import com.sf.jkt.j.spring.biz.dto.entity.OrderItemDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper MAPPER = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toOrder(OrderItemDto orderItemDto);

    @InheritInverseConfiguration
    OrderItemDto fromOrder(OrderItem orderItem);
}