package com.example.car_repair_shop.dto.order;

import com.example.car_repair_shop.entity.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderStatusHistoryDto(
        OrderStatus fromStatus,
        OrderStatus toStatus,
        String comment,
        String changedBy,
        LocalDateTime changedAt
) {
}
