package com.srikant.ecommerce.payment.kafka;

public record PaymentEvent(Long orderId, boolean successful, String reason) {}
