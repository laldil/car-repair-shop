package com.example.car_repair_shop.service.order;

import com.example.car_repair_shop.dto.PageDto;
import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;
import com.example.car_repair_shop.dto.order.CreateOrderRequest;
import com.example.car_repair_shop.dto.order.OrderDto;
import com.example.car_repair_shop.entity.enums.OrderStatus;

import java.util.UUID;

public interface OrderService {

    OrderDto createOrder(CreateOrderRequest request);

    OrderDto getOrder(UUID id);

    PageDto<OrderDto> getOrdersByCustomer(Long customerId, int page, int size);

    PageDto<OrderDto> getOrders(OrderStatus status, int page, int size);

    OrderDto changeStatus(UUID id, ChangeOrderStatusRequest request);

    OrderDto changeStatus(UUID id, ChangeOrderStatusRequest request, String changedBy);
}
