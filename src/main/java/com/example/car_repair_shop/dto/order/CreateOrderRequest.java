package com.example.car_repair_shop.dto.order;

import jakarta.validation.constraints.NotBlank;

public record CreateOrderRequest(
        @NotBlank
        String carNumber,

        @NotBlank
        String carModel,

        @NotBlank
        String description
) {
}
