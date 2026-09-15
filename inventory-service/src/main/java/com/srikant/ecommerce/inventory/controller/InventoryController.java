package com.srikant.ecommerce.inventory.controller;

import com.srikant.ecommerce.inventory.entity.Inventory;
import com.srikant.ecommerce.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory create(@RequestBody Inventory inventory) {
        return repository.save(inventory);
    }

    @GetMapping
    public List<Inventory> all() { return repository.findAll(); }

    @GetMapping("/{productId}")
    public Inventory get(@PathVariable Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}
