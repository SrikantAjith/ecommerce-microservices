package com.srikant.ecommerce.notification.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "notification-service", "status", "UP");
    }
}
