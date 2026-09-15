package com.srikant.ecommerce.order.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(Long productId, Integer quantity, BigDecimal amount) {}
