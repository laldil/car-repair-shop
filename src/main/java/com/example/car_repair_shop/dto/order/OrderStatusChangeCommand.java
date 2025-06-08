package com.example.car_repair_shop.dto.order;

import com.example.car_repair_shop.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangeCommand {
    private UUID commandId;
    private UUID orderId;
    private OrderStatus newStatus;
    private String comment;
    private String changedBy;

    public OrderStatusChangeCommand(UUID orderId, OrderStatus newStatus, String comment, String changedBy) {
        this.commandId = UUID.randomUUID();
        this.orderId = orderId;
        this.newStatus = newStatus;
        this.comment = comment;
        this.changedBy = changedBy;
    }
}
