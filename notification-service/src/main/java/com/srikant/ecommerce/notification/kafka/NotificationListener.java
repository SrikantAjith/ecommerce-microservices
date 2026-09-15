package com.srikant.ecommerce.notification.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @KafkaListener(
            topics = {"inventory-events", "payment-events"},
            groupId = "notification-group")
    public void consume(String message) {
        log.info("NOTIFICATION EVENT RECEIVED: {}", message);
        // Real implementation could send email/SMS/push notification.
    }
}
