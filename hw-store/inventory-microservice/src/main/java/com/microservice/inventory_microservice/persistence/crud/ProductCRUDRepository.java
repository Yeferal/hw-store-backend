package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCRUDRepository extends JpaRepository<Product, Long> {
    boolean existsByCode(String code);
}