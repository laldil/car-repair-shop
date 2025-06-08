package com.example.car_repair_shop.repository;

import com.example.car_repair_shop.entity.ProcessedStatusChangeCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcessedStatusChangeCommandRepository extends JpaRepository<ProcessedStatusChangeCommandEntity, UUID> {

    boolean existsById(UUID id);
}
