package com.example.car_repair_shop.service.order.impl;

import com.example.car_repair_shop.dto.order.OrderStatusHistoryDto;
import com.example.car_repair_shop.mapper.OrderStatusHistoryMapper;
import com.example.car_repair_shop.repository.OrderStatusHistoryRepository;
import com.example.car_repair_shop.service.order.OrderStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {

    private final OrderStatusHistoryRepository orderStatusHistoryRepository;

    @Override
    public List<OrderStatusHistoryDto> getStatusHistory(UUID orderId) {
        return orderStatusHistoryRepository.findByOrderId(orderId).stream()
                .map(OrderStatusHistoryMapper.MAPPER::toDto)
                .toList();
    }
}
