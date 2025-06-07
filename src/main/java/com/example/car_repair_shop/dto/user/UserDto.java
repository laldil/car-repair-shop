package com.example.car_repair_shop.dto.user;

import com.example.car_repair_shop.entity.enums.Role;

public record UserDto(String username, Role role) {
}
