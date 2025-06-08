package com.example.car_repair_shop.service.notification.factory;

import com.example.car_repair_shop.dto.notification.NotificationType;
import com.example.car_repair_shop.service.notification.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationFactory {

    private final Map<NotificationType, NotificationSender> senderMap = new HashMap<>();

    @Autowired
    public NotificationFactory(List<NotificationSender> senders) {
        senders.forEach(sender -> senderMap.put(sender.getType(), sender));
    }

    public NotificationSender getSender(NotificationType type) {
        var sender = senderMap.get(type);
        if (sender == null) {
            throw new IllegalArgumentException("Unsupported contact type: " + type);
        }
        return sender;
    }
}