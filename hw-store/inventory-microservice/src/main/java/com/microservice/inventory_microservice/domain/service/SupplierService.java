package com.microservice.inventory_microservice.domain.service;

import com.microservice.inventory_microservice.domain.dto.SupplierDTO;

public interface SupplierService {

    SupplierDTO getSupplier(Long id);
}
