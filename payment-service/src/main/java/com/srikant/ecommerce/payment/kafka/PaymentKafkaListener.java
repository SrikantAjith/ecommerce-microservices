package com.srikant.ecommerce.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srikant.ecommerce.payment.entity.Payment;
import com.srikant.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaListener {
    private final PaymentRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-events", groupId = "payment-group")
    public void consume(String message) throws Exception {
        OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);

        boolean success = event.amount() != null && event.amount().signum() > 0;

        Payment payment = Payment.builder()
                .orderId(event.orderId())
                .amount(event.amount())
                .status(success ? "SUCCESS" : "FAILED")
                .build();

        repository.save(payment);

        kafkaTemplate.send("payment-events",
                new PaymentEvent(event.orderId(), success,
                        success ? "Payment successful" : "Invalid payment amount"));
    }
}
