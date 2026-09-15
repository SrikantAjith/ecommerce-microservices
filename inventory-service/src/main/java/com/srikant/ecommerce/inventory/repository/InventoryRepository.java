package com.srikant.ecommerce.inventory.repository;

import com.srikant.ecommerce.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
