package com.srikant.ecommerce.inventory.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srikant.ecommerce.inventory.entity.Inventory;
import com.srikant.ecommerce.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryKafkaListener {
    private final InventoryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(String message) throws Exception {
        OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
        Inventory inventory = repository.findById(event.productId()).orElse(null);

        boolean reserved = inventory != null && inventory.getQuantity() >= event.quantity();
        String reason = reserved ? "Inventory reserved" : "Insufficient inventory";

        if (reserved) {
            inventory.setQuantity(inventory.getQuantity() - event.quantity());
            repository.save(inventory);
        }

        kafkaTemplate.send("inventory-events",
                new InventoryEvent(event.orderId(), reserved, reason));
    }
}
