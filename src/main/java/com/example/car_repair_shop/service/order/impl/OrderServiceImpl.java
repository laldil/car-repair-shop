package com.example.car_repair_shop.service.order.impl;

import com.example.car_repair_shop.dto.PageDto;
import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;
import com.example.car_repair_shop.dto.order.CreateOrderRequest;
import com.example.car_repair_shop.dto.order.OrderDto;
import com.example.car_repair_shop.entity.enums.OrderStatus;
import com.example.car_repair_shop.event.OrderStatusChangedEvent;
import com.example.car_repair_shop.exception.NotFoundException;
import com.example.car_repair_shop.mapper.OrderMapper;
import com.example.car_repair_shop.repository.OrderRepository;
import com.example.car_repair_shop.repository.UserRepository;
import com.example.car_repair_shop.security.SecurityUtils;
import com.example.car_repair_shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderDto createOrder(CreateOrderRequest request) {
        var customer = userRepository.findById(SecurityUtils.getCurrentId())
                .orElseThrow(() -> new UsernameNotFoundException("Customer with id %d not found".formatted(SecurityUtils.getCurrentId())));

        var order = OrderMapper.MAPPER.mapToEntity(request);
        order.setCustomer(customer);

        order = orderRepository.save(order);
        return OrderMapper.MAPPER.mapToDto(order);
    }

    @Override
    public OrderDto getOrder(UUID id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id %s not found".formatted(id)));
        return OrderMapper.MAPPER.mapToDto(order);
    }

    @Override
    public PageDto<OrderDto> getOrdersByCustomer(Long customerId, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var ordersPage = orderRepository.findByCustomerId(customerId, pageRequest);

        var orders = ordersPage.map(OrderMapper.MAPPER::mapToDto).getContent();
        return new PageDto<>(orders, ordersPage.getTotalElements());
    }

    @Override
    public PageDto<OrderDto> getOrders(OrderStatus status, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var ordersPage = orderRepository.findByStatus(status, pageRequest);

        var orders = ordersPage.map(OrderMapper.MAPPER::mapToDto).getContent();
        return new PageDto<>(orders, ordersPage.getTotalElements());
    }

    @Override
    public OrderDto changeStatus(UUID id, ChangeOrderStatusRequest request) {
        return changeStatus(id, request, SecurityUtils.getCurrentUsername());
    }

    @Override
    public OrderDto changeStatus(UUID id, ChangeOrderStatusRequest request, String changedBy) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id %s not found".formatted(id)));

        var oldStatus = order.getStatus();
        var newStatus = request.newStatus();

        if (!oldStatus.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Cannot transition from %s to %s".formatted(oldStatus, newStatus));
        }

        order.setStatus(newStatus);
        order = orderRepository.save(order);

        eventPublisher.publishEvent(new OrderStatusChangedEvent(
                order.getId(), oldStatus, newStatus, request.comment(), changedBy
        ));

        return OrderMapper.MAPPER.mapToDto(order);
    }
}