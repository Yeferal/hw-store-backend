package com.microservice.inventory_microservice.domain.service;

import com.microservice.inventory_microservice.domain.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductDefaultDTO addProduct(ProductBodyDTO productBodyDTO, String token);
    ProductDefaultDTO getProduct(Long id);
    ProductDefaultDTO getProductByCode(String code);
    Page<ProductDefaultDTO> getAllProducts(PaginateAndSortDTO paginateAndSortDTO);
    BrandDTO addBrand(BrandBodyDTO brandBodyDTO);
    Page<BrandDTO> getBrands(PaginateAndSortDTO paginateAndSortDTO);
    List<BrandDTO> getAllBrands();
    CategoryDTO addCategory(CategoryBodyDTO categoryBodyDTO);
    Page<CategoryDTO> getCategories(PaginateAndSortDTO paginateAndSortDTO);
    List<CategoryDTO> getAllCategories();
    MeasurementUnitDTO addMeasurementUnit(MeasurementUnitBodyDTO measurementUnitBodyDTO);
    Page<MeasurementUnitDTO> getMeasurementUnits(PaginateAndSortDTO paginateAndSortDTO);
    List<MeasurementUnitDTO> getAllMeasurementUnits();

}
