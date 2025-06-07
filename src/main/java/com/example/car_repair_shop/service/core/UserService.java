package com.example.car_repair_shop.service.core;

import com.example.car_repair_shop.dto.user.CreateUserRequest;
import com.example.car_repair_shop.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity createUser(CreateUserRequest request);

    UserEntity findByUsername(String username);
}
