package com.srikant.ecommerce.payment.controller;

import com.srikant.ecommerce.payment.entity.Payment;
import com.srikant.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentRepository repository;

    @GetMapping
    public List<Payment> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Payment get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
