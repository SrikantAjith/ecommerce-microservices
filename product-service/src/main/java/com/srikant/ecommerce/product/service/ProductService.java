package com.srikant.ecommerce.product.service;

import com.srikant.ecommerce.product.entity.Product;
import com.srikant.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product create(Product product) { return repository.save(product); }
    public List<Product> getAll() { return repository.findAll(); }
    public Product get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }
    public Product update(Long id, Product input) {
        Product p = get(id);
        p.setName(input.getName());
        p.setDescription(input.getDescription());
        p.setPrice(input.getPrice());
        p.setCategory(input.getCategory());
        return repository.save(p);
    }
    public void delete(Long id) { repository.delete(get(id)); }
}
