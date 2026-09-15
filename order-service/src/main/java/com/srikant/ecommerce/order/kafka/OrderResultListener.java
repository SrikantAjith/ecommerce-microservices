package com.srikant.ecommerce.order.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srikant.ecommerce.order.entity.Order;
import com.srikant.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderResultListener {
    private final OrderRepository repository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "inventory-events", groupId = "order-inventory-group")
    public void inventoryResult(String message) throws Exception {
        ResultEvent event = objectMapper.readValue(message, ResultEvent.class);
        updateStatus(event, "INVENTORY");
    }

    @KafkaListener(topics = "payment-events", groupId = "order-payment-group")
    public void paymentResult(String message) throws Exception {
        ResultEvent event = objectMapper.readValue(message, ResultEvent.class);
        updateStatus(event, "PAYMENT");
    }

    private void updateStatus(ResultEvent event, String source) {
        Order order = repository.findById(event.orderId()).orElse(null);
        if (order == null) return;

        if (!event.successful()) {
            order.setStatus("CANCELLED");
        } else if ("PAYMENT".equals(source)) {
            order.setStatus("CONFIRMED");
        }

        repository.save(order);
    }
}
