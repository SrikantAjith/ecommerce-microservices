package com.srikant.ecommerce.order.kafka;

public record ResultEvent(Long orderId, boolean successful, String reason) {}
