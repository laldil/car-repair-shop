package com.example.car_repair_shop.dto.notification;

public record NotificationDto(
        String title,
        String content,
        String contact,
        NotificationType type
) {
}
