package com.example.car_repair_shop.service.core.impl;

import com.example.car_repair_shop.dto.user.CreateUserRequest;
import com.example.car_repair_shop.entity.UserEntity;
import com.example.car_repair_shop.exception.UserAlreadyExistsException;
import com.example.car_repair_shop.mapper.UserMapper;
import com.example.car_repair_shop.repository.UserRepository;
import com.example.car_repair_shop.service.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("Username %s already exists".formatted(request.username()));
        }

        var user = UserMapper.MAPPER.mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = findByUsername(username);

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().name())))
                .build();
    }
}
