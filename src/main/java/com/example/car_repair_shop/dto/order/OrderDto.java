package com.example.car_repair_shop.dto.order;

import com.example.car_repair_shop.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        UUID id,
        String carModel,
        String carNumber,
        String description,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
