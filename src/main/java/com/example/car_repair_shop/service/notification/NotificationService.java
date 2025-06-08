package com.example.car_repair_shop.service.notification;

import com.example.car_repair_shop.dto.notification.NotificationDto;

public interface NotificationService {

    void sendNotification(NotificationDto notification);
}
