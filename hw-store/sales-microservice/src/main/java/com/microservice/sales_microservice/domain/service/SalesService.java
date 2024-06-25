package com.microservice.sales_microservice.domain.service;

import com.microservice.sales_microservice.domain.dto.SaleBodyDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;

public interface SalesService {
    SaleDTO createSale(SaleBodyDTO saleBodyDTO, String token);
}
