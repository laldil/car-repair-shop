package com.example.car_repair_shop.controller;

import com.example.car_repair_shop.dto.auth.JwtResponse;
import com.example.car_repair_shop.dto.auth.LoginRequest;
import com.example.car_repair_shop.dto.auth.RefreshTokenRequest;
import com.example.car_repair_shop.dto.user.CreateUserRequest;
import com.example.car_repair_shop.service.core.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(authService.register(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok().body(authService.refreshAccessToken(request));
    }
}
