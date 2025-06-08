package com.example.car_repair_shop.controller;

import com.example.car_repair_shop.dto.PageDto;
import com.example.car_repair_shop.dto.order.ChangeOrderStatusRequest;
import com.example.car_repair_shop.dto.order.CreateOrderRequest;
import com.example.car_repair_shop.dto.order.OrderDto;
import com.example.car_repair_shop.dto.order.OrderStatusHistoryDto;
import com.example.car_repair_shop.entity.enums.OrderStatus;
import com.example.car_repair_shop.service.order.OrderService;
import com.example.car_repair_shop.service.order.OrderStatusHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderStatusHistoryService orderStatusHistoryService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable UUID id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<PageDto<OrderDto>> getByCustomer(@PathVariable Long customerId,
                                                           @RequestParam(value = "size", defaultValue = "20") int size,
                                                           @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok().body(orderService.getOrdersByCustomer(customerId, page, size));
    }

    @GetMapping
    public ResponseEntity<PageDto<OrderDto>> getByStatus(@RequestParam OrderStatus status,
                                                         @RequestParam(value = "size", defaultValue = "20") int size,
                                                         @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(orderService.getOrders(status, page, size));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> changeStatus(@PathVariable UUID id,
                                                 @RequestBody @Valid ChangeOrderStatusRequest request) {
        return ResponseEntity.ok(orderService.changeStatus(id, request));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<OrderStatusHistoryDto>> getOrderHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(orderStatusHistoryService.getStatusHistory(id));
    }
}
