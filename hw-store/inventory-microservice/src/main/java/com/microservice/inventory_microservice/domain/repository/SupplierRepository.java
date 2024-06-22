package com.microservice.inventory_microservice.domain.repository;

import com.microservice.inventory_microservice.persistence.model.Supplier;

import java.util.Optional;

public interface SupplierRepository {
    Supplier createSupplier(Supplier supplier);
    Optional<Supplier> getSupplierById(Long supplierId);
}
