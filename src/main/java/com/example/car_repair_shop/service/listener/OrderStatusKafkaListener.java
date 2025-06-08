package com.example.car_repair_shop.service.listener;

import com.example.car_repair_shop.config.KafkaTopicConfig;
import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;
import com.example.car_repair_shop.dto.order.OrderStatusChangeCommand;
import com.example.car_repair_shop.exception.NotFoundException;
import com.example.car_repair_shop.repository.ProcessedStatusChangeCommandRepository;
import com.example.car_repair_shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderStatusKafkaListener {

    private final OrderService orderService;
    private final ProcessedStatusChangeCommandRepository processedStatusChangeCommandRepository;

    @KafkaListener(topics = KafkaTopicConfig.ORDER_STATUS_COMMANDS_TOPIC, groupId = "car-repair-service")
    public void handle(OrderStatusChangeCommand command) {
        log.info("Received status change command from Kafka: {}", command);
        var request = new ChangeOrderStatusRequest(command.getNewStatus(), command.getComment());

        if (processedStatusChangeCommandRepository.existsById(command.getCommandId())) {
            log.info("Command {} already processed", command.getCommandId());
            return;
        }

        try {
            orderService.changeStatus(command.getOrderId(), request, command.getChangedBy());
            log.info("Successfully changed order status for orderId={}", command.getOrderId());
        } catch (NotFoundException e) {
            log.error("Order not found: {}", command.getOrderId(), e);
        } catch (Exception e) {
            log.error("Unexpected error while handling order status change command: {}", command, e);
            throw e;
        }
    }
}
