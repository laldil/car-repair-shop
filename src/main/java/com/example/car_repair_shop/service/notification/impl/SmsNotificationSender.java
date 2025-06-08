package com.example.car_repair_shop.service.notification.impl;

import com.example.car_repair_shop.dto.notification.NotificationType;
import com.example.car_repair_shop.service.notification.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsNotificationSender implements NotificationSender {

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    public Boolean sendNotification(String contact, String title, String message) {
        log.info("Sending SMS notification to contact: {} \n" +
                "With title: {} \n" +
                "With message: {}", contact, title, message);
        return true;
    }
}
