package com.example.car_repair_shop.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull
        @NotBlank
        String username,

        @NotNull
        String password
) {
}
