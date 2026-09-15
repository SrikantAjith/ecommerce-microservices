package com.srikant.ecommerce.order.controller;

import com.srikant.ecommerce.order.dto.CreateOrderRequest;
import com.srikant.ecommerce.order.entity.Order;
import com.srikant.ecommerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return service.get(id);
    }
}
