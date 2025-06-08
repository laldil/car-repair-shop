package com.example.car_repair_shop.service.notification;

import com.example.car_repair_shop.dto.notification.NotificationType;

public interface NotificationSender {

    NotificationType getType();

    Boolean sendNotification(String contact, String title, String message);
}
