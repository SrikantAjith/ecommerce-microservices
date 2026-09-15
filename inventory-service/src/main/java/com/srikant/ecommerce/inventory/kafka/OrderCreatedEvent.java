package com.srikant.ecommerce.inventory.kafka;

public record OrderCreatedEvent(Long orderId, Long productId, int quantity) {}
