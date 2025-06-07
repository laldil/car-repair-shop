package com.example.car_repair_shop.dto.auth;

import com.example.car_repair_shop.dto.user.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {

    private final String tokenType = "Bearer ";

    private UserDto user;
    private String accessToken;
    private String refreshToken;
}
