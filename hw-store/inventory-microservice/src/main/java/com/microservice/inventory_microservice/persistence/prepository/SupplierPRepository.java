package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.repository.SupplierRepository;
import com.microservice.inventory_microservice.persistence.crud.SupplierCRUDRepository;
import com.microservice.inventory_microservice.persistence.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SupplierPRepository implements SupplierRepository {
    @Autowired
    private SupplierCRUDRepository supplierCRUDRepository;

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierCRUDRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> getSupplierById(Long supplierId) {
        return supplierCRUDRepository.findById(supplierId);
    }
}
