package com.microservice.sales_microservice.domain.repository;

import com.microservice.sales_microservice.persistence.model.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository {

    Product updateProduct(Product product);
    Optional<Product> getProductById(Long id);
    Product updateStock(Long productId, BigDecimal amount);
}
