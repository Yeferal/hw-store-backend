package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierCRUDRepository extends JpaRepository<Supplier, Long> {
}