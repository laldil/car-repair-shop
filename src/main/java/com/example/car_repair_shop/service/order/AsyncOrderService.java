package com.example.car_repair_shop.service.order;

import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;

import java.util.UUID;

public interface AsyncOrderService {

    void submitStatusChange(UUID orderId, ChangeOrderStatusRequest request, String changedBy);
}
