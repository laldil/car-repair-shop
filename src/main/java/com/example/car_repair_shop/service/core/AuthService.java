package com.example.car_repair_shop.service.core;

import com.example.car_repair_shop.dto.auth.JwtResponse;
import com.example.car_repair_shop.dto.auth.LoginRequest;
import com.example.car_repair_shop.dto.auth.RefreshTokenRequest;
import com.example.car_repair_shop.dto.user.CreateUserRequest;

public interface AuthService {

    JwtResponse register(CreateUserRequest request);

    JwtResponse login(LoginRequest request);

    JwtResponse refreshAccessToken(RefreshTokenRequest refreshToken);
}