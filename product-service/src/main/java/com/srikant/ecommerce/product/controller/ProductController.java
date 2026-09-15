package com.srikant.ecommerce.product.controller;

import com.srikant.ecommerce.product.entity.Product;
import com.srikant.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product p) { return service.create(p); }

    @GetMapping
    public List<Product> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) { return service.update(id, p); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
