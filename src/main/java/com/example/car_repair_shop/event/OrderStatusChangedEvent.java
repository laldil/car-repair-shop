package com.example.car_repair_shop.event;

import com.example.car_repair_shop.entity.enums.OrderStatus;

import java.util.UUID;

public record OrderStatusChangedEvent(
        UUID orderId,
        OrderStatus from,
        OrderStatus to,
        String comment,
        String changedBy
) {
}
