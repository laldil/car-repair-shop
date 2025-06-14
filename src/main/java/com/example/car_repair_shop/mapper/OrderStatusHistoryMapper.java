package com.example.car_repair_shop.mapper;

import com.example.car_repair_shop.dto.order.OrderStatusHistoryDto;
import com.example.car_repair_shop.entity.OrderStatusHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderStatusHistoryMapper {

    OrderStatusHistoryMapper MAPPER = Mappers.getMapper(OrderStatusHistoryMapper.class);

    OrderStatusHistoryDto toDto(OrderStatusHistoryEntity orderStatusHistoryEntity);
}
