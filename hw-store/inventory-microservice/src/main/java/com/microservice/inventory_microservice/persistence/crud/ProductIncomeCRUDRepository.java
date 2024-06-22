package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.ProductIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIncomeCRUDRepository extends JpaRepository<ProductIncome, Long> {
}