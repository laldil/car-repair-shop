package com.example.car_repair_shop.service.order;

import com.example.car_repair_shop.dto.order.OrderStatusHistoryDto;

import java.util.List;
import java.util.UUID;

public interface OrderStatusHistoryService {

    List<OrderStatusHistoryDto> getStatusHistory(UUID orderId);
}
