package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.repository.ProductRepository;
import com.microservice.inventory_microservice.persistence.crud.*;
import com.microservice.inventory_microservice.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPRepository implements ProductRepository {
    @Autowired
    private ProductCRUDRepository productCRUDRepository;
    @Autowired
    private BrandCRUDRepository brandCRUDRepository;
    @Autowired
    private CategoryCRUDRepository categoryCRUDRepository;
    @Autowired
    private MeasurementUnitCRUDRepository measurementUnitCRUDRepository;
    @Autowired
    private AssignmentCategoryCRUDRepository assignmentCategoryCRUDRepository;
    @Autowired
    private AssignmentMeasureCRUDRepository assignmentMeasureCRUDRepository;

    @Override
    public Product createProduct(Product product) {
        return productCRUDRepository.save(product);
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandCRUDRepository.save(brand);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryCRUDRepository.save(category);
    }

    @Override
    public MeasurementUnit createMeasurementUnit(MeasurementUnit measurementUnit) {
        return measurementUnitCRUDRepository.save(measurementUnit);
    }

    @Override
    public AssignmentMeasure createAssignmentMeasure(AssignmentMeasure assignmentMeasure) {
        return assignmentMeasureCRUDRepository.save(assignmentMeasure);
    }

    @Override
    public AssignmentCategory createAssignmentCategory(AssignmentCategory assignmentCategory) {
        return assignmentCategoryCRUDRepository.save(assignmentCategory);
    }

    @Override
    public boolean isExistedProduct(String code) {
        return productCRUDRepository.existsByCode(code);
    }

    @Override
    public boolean isExistedBrand(String name) {
        return brandCRUDRepository.existsByName(name);
    }

    @Override
    public boolean isExistedCategory(String name) {
        return categoryCRUDRepository.existsByName(name);
    }

    @Override
    public boolean isExistedMeasurementUnit(String name) {
        return measurementUnitCRUDRepository.existsByName(name);
    }

}
