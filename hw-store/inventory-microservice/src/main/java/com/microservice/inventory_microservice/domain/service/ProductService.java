package com.microservice.inventory_microservice.domain.service;

import com.microservice.inventory_microservice.domain.dto.*;

public interface ProductService {

    ProductDefaultDTO addProduct(ProductBodyDTO productBodyDTO);
    BrandDTO addBrand(BrandBodyDTO brandBodyDTO);
    CategoryDTO addCategory(CategoryBodyDTO categoryBodyDTO);
    MeasurementUnitDTO addMeasurementUnit(MeasurementUnitBodyDTO measurementUnitBodyDTO);
}
