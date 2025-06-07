package com.example.car_repair_shop.dto.auth;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull
        String refreshToken
) {
}
