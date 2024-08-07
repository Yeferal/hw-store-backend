package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCRUDRepository extends JpaRepository<Product, Long> {
}