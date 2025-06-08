package com.example.car_repair_shop.service.listener;

import com.example.car_repair_shop.dto.notification.NotificationDto;
import com.example.car_repair_shop.dto.notification.NotificationType;
import com.example.car_repair_shop.entity.enums.OrderStatus;
import com.example.car_repair_shop.event.OrderStatusChangedEvent;
import com.example.car_repair_shop.repository.OrderRepository;
import com.example.car_repair_shop.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderCompletionNotificationListener {

    private final NotificationService notificationService;

    private final OrderRepository orderRepository;

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyIfCompleted(OrderStatusChangedEvent event) {
        if (event.to() != OrderStatus.COMPLETED) {
            return;
        }
        var order = orderRepository.findWithCustomerById(event.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        var customer = order.getCustomer();

        // TODO: Hardcoded values
        var notificationDto = new NotificationDto(
                "Repair completion",
                "Your car is ready, you can pick it up",
                customer.getPhoneNumber(),
                NotificationType.SMS
        );
        notificationService.sendNotification(notificationDto);
    }

}
