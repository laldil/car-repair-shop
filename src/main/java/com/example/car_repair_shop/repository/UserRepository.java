package com.example.car_repair_shop.repository;

import com.example.car_repair_shop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
}
