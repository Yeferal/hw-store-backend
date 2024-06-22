package com.microservice.inventory_microservice.domain.service;

import com.microservice.inventory_microservice.domain.dto.*;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDefaultDTO addProduct(ProductBodyDTO productBodyDTO, String token);
    Page<ProductDefaultDTO> getAllProducts(PaginateAndSortDTO paginateAndSortDTO);
    BrandDTO addBrand(BrandBodyDTO brandBodyDTO);
    CategoryDTO addCategory(CategoryBodyDTO categoryBodyDTO);
    MeasurementUnitDTO addMeasurementUnit(MeasurementUnitBodyDTO measurementUnitBodyDTO);

}
