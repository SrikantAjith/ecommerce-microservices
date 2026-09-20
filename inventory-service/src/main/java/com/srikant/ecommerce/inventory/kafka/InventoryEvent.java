package com.srikant.ecommerce.inventory.kafka;

public record InventoryEvent(Long orderId, boolean reserved, String reason) {
}