package com.microservice.inventory_microservice.domain.repository;

import com.microservice.inventory_microservice.persistence.model.*;

public interface ProductRepository {

    Product createProduct(Product product);
    Brand createBrand(Brand brand);
    Category createCategory(Category category);
    MeasurementUnit createMeasurementUnit(MeasurementUnit measurementUnit);
    AssignmentMeasure createAssignmentMeasure(AssignmentMeasure assignmentMeasure);
    AssignmentCategory createAssignmentCategory(AssignmentCategory assignmentCategory);
    boolean isExistedProduct(String code);
    boolean isExistedBrand(String name);
    boolean isExistedCategory(String name);
    boolean isExistedMeasurementUnit(String name);
}
