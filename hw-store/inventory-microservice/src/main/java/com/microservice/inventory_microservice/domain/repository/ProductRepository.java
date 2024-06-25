package com.microservice.inventory_microservice.domain.repository;

import com.microservice.inventory_microservice.domain.dto.ProductDefaultDTO;
import com.microservice.inventory_microservice.persistence.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ProductRepository {

    Product createProduct(Product product);
    Product updateProduct(Product product);
    Brand createBrand(Brand brand);
    Category createCategory(Category category);
    MeasurementUnit createMeasurementUnit(MeasurementUnit measurementUnit);
    AssignmentMeasure createAssignmentMeasure(AssignmentMeasure assignmentMeasure);
    AssignmentCategory createAssignmentCategory(AssignmentCategory assignmentCategory);
    boolean isExistedProduct(String code);
    boolean isExistedBrand(String name);
    boolean isExistedCategory(String name);
    boolean isExistedMeasurementUnit(String name);
    Optional<Product> getProductById(Long id);
    Optional<Category> getCategoryById(Long id);
    Optional<Brand> getBrandById(Long id);
    Optional<MeasurementUnit> getMeasureUnitById(Long id);
    ProductImage createProductImage(ProductImage productImage);
    Page<Product> getAllProductPaginateAndSort(Specification<Product> specs, Pageable pageable);
}
