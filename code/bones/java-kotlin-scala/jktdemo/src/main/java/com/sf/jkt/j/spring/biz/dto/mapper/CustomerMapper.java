package com.sf.jkt.j.spring.biz.dto.mapper;

import com.sf.jkt.j.spring.biz.dto.entity.Customer;
import com.sf.jkt.j.spring.biz.dto.entity.CustomerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { OrderItemMapper.class })
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper( CustomerMapper.class );

    @Mappings({
        @Mapping(source = "orders", target = "orderItems"),
        @Mapping(source = "customerName", target = "name")
    })
    Customer toCustomer(CustomerDto customerDto);

    @InheritInverseConfiguration
    CustomerDto fromCustomer(Customer customer);
}