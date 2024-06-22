package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseCRUDRepository extends JpaRepository<Purchase, Long> {
}