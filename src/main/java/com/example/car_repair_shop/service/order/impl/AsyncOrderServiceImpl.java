package com.example.car_repair_shop.service.order.impl;

import com.example.car_repair_shop.config.KafkaTopicConfig;
import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;
import com.example.car_repair_shop.dto.order.OrderStatusChangeCommand;
import com.example.car_repair_shop.service.order.AsyncOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AsyncOrderServiceImpl implements AsyncOrderService {

    private final KafkaTemplate<String, OrderStatusChangeCommand> kafkaTemplate;

    @Override
    public void submitStatusChange(UUID orderId, ChangeOrderStatusRequest request, String changedBy) {
        var message = new OrderStatusChangeCommand(orderId, request.newStatus(), request.comment(), changedBy);

        log.info("Sending status change command to Kafka: {}", message);

        kafkaTemplate.send(KafkaTopicConfig.ORDER_STATUS_COMMANDS_TOPIC, orderId.toString(), message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send status change command to Kafka for orderId={}", orderId, ex);
                    } else {
                        var metadata = result.getRecordMetadata();
                        log.info("Successfully sent message to Kafka: topic={}, partition={}, offset={}",
                                metadata.topic(), metadata.partition(), metadata.offset());
                    }
                });
    }
}
