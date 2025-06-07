package com.example.car_repair_shop.dto.user;

import com.example.car_repair_shop.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull
        @NotBlank
        String username,

        @NotNull
        String password,

        @NotNull
        Role role
) {
}
