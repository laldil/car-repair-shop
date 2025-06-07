package com.example.car_repair_shop.dto.order;

import com.example.car_repair_shop.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeOrderStatusRequest(
        @NotNull
        OrderStatus newStatus,

        String comment
) {
}
