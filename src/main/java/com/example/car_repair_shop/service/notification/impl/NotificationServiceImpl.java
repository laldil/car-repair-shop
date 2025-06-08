package com.example.car_repair_shop.service.notification.impl;

import com.example.car_repair_shop.dto.notification.NotificationDto;
import com.example.car_repair_shop.service.notification.NotificationService;
import com.example.car_repair_shop.service.notification.factory.NotificationFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationFactory;

    @Override
    public void sendNotification(NotificationDto request) {
        try {
            var sender = notificationFactory.getSender(request.type());

            var isSent = sender.sendNotification(request.contact(), request.title(), request.content());
            if (isSent) {
                log.info("Notification successfully sent to: {}", request.contact());
            }
        } catch (Exception e) {
            log.error("Error while sending notification to {}: {}", request.contact(), e.getMessage());
            throw e;
        }
    }
}
