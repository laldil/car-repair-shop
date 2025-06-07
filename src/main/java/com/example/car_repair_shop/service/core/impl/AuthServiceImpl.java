package com.example.car_repair_shop.service.core.impl;

import com.example.car_repair_shop.dto.auth.JwtResponse;
import com.example.car_repair_shop.dto.auth.LoginRequest;
import com.example.car_repair_shop.dto.auth.RefreshTokenRequest;
import com.example.car_repair_shop.dto.user.CreateUserRequest;
import com.example.car_repair_shop.entity.UserEntity;
import com.example.car_repair_shop.exception.InvalidCredentialsException;
import com.example.car_repair_shop.exception.InvalidTokenException;
import com.example.car_repair_shop.mapper.UserMapper;
import com.example.car_repair_shop.security.JwtService;
import com.example.car_repair_shop.service.core.AuthService;
import com.example.car_repair_shop.service.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public JwtResponse register(CreateUserRequest request) {
        var user = userService.createUser(request);
        return buildJwtResponse(user);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Username or password is not correct");
        }

        var user = userService.findByUsername(request.username());
        return buildJwtResponse(user);
    }

    @Override
    public JwtResponse refreshAccessToken(RefreshTokenRequest request) {
        var refreshToken = request.refreshToken();
        if (!jwtService.validateToken(refreshToken)) {
            throw new InvalidTokenException("Refresh token is invalid or expired");
        }

        var username = jwtService.getUsername(refreshToken);
        var user = userService.findByUsername(username);

        return buildJwtResponse(user);
    }

    private JwtResponse buildJwtResponse(UserEntity user) {
        return JwtResponse.builder()
                .user(UserMapper.MAPPER.mapToDto(user))
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }
}
