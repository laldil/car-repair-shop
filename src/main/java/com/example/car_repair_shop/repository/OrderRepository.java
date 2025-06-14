package com.example.car_repair_shop.repository;

import com.example.car_repair_shop.entity.OrderEntity;
import com.example.car_repair_shop.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    Page<OrderEntity> findByCustomerId(Long customerId, Pageable pageable);

    Page<OrderEntity> findByStatus(OrderStatus orderStatus, Pageable pageable);

    @Query("SELECT o FROM OrderEntity o JOIN FETCH o.customer WHERE o.id = :id")
    Optional<OrderEntity> findWithCustomerById(UUID id);
}
