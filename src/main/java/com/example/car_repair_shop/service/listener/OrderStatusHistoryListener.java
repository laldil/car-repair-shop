package com.example.car_repair_shop.event;

import com.example.car_repair_shop.entity.OrderStatusHistoryEntity;
import com.example.car_repair_shop.repository.OrderRepository;
import com.example.car_repair_shop.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderStatusHistoryListener {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository historyRepository;

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onStatusChanged(OrderStatusChangedEvent event) {
        var order = orderRepository.findById(event.orderId())
                .orElseThrow(() -> new IllegalStateException("Order not found for history"));

        var history = new OrderStatusHistoryEntity();
        history.setOrder(order);
        history.setFromStatus(event.from());
        history.setToStatus(event.to());
        history.setComment(event.comment());
        history.setChangedBy(event.changedBy());

        historyRepository.save(history);
    }
}
