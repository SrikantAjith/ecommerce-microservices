package com.srikant.ecommerce.order.service;

import com.srikant.ecommerce.order.dto.CreateOrderRequest;
import com.srikant.ecommerce.order.entity.Order;
import com.srikant.ecommerce.order.kafka.OrderCreatedEvent;
import com.srikant.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Order create(CreateOrderRequest request) {
        Order order = Order.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .amount(request.amount())
                .status("PENDING")
                .build();

        order = repository.save(order);

        kafkaTemplate.send("order-events",
                new OrderCreatedEvent(
                        order.getId(),
                        order.getProductId(),
                        order.getQuantity(),
                        order.getAmount()));

        return order;
    }

    public Order get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
