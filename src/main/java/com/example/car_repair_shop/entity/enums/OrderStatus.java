package com.example.car_repair_shop.entity.enums;

import java.util.Collections;
import java.util.Set;

public enum OrderStatus {
    NEW,
    ACCEPTED,
    IN_PROGRESS,
    REPAIR,
    COMPLETED;

    public boolean canTransitionTo(OrderStatus targetStatus) {
        return getAllowedTransitions().contains(targetStatus);
    }

    private Set<OrderStatus> getAllowedTransitions() {
        return switch (this) {
            case NEW -> Set.of(ACCEPTED);
            case ACCEPTED -> Set.of(IN_PROGRESS);
            case IN_PROGRESS -> Set.of(REPAIR);
            case REPAIR -> Set.of(COMPLETED);
            case COMPLETED -> Collections.emptySet();
        };
    }
}
