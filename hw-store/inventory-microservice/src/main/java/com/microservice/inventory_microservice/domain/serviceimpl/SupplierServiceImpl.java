package com.microservice.inventory_microservice.domain.serviceimpl;

import com.microservice.inventory_microservice.domain.dto.SupplierDTO;
import com.microservice.inventory_microservice.domain.map.SupplierMapper;
import com.microservice.inventory_microservice.domain.repository.SupplierRepository;
import com.microservice.inventory_microservice.domain.service.SupplierService;
import com.microservice.inventory_microservice.persistence.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public SupplierDTO getSupplier(Long supplierId) {
        Optional<Supplier> supplier = supplierRepository.getSupplierById(supplierId);
        return supplier.map(value -> supplierMapper.toDto(value)).orElse(null);
    }
}
