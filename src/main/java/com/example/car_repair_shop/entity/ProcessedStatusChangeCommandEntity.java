package com.example.car_repair_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "processed_status_change_command")
@Entity
public class ProcessedStatusChangeCommandEntity {

    @Id
    private UUID id;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    public ProcessedStatusChangeCommandEntity(UUID id) {
        this.id = id;
        processedAt = LocalDateTime.now();
    }
}
