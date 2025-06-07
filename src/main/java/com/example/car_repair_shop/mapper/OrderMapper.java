package com.example.car_repair_shop.mapper;

import com.example.car_repair_shop.dto.order.CreateOrderRequest;
import com.example.car_repair_shop.dto.order.OrderDto;
import com.example.car_repair_shop.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderEntity mapToEntity(CreateOrderRequest request);

    OrderDto mapToDto(OrderEntity entity);
}
