package com.srikant.ecommerce.order.kafka;

import java.math.BigDecimal;

public record OrderCreatedEvent(Long orderId, Long productId, int quantity, BigDecimal amount) {}
