package com.microservice.inventory_microservice.domain.service;

import com.microservice.inventory_microservice.domain.dto.ProductIncomeBodyDTO;
import com.microservice.inventory_microservice.domain.dto.ProductIncomeDTO;

public interface ProductIncomeService {

    ProductIncomeDTO addIncomeProduct(ProductIncomeBodyDTO productIncomeBodyDTO, String token);
}
