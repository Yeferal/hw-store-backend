package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.dto.ProductDefaultDTO;
import com.microservice.inventory_microservice.domain.repository.ProductRepository;
import com.microservice.inventory_microservice.persistence.crud.*;
import com.microservice.inventory_microservice.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductPRepository implements ProductRepository {
    @Autowired
    private ProductCRUDRepository productCRUDRepository;
    @Autowired
    private ProductImageCRUDRepository productImageCRUDRepository;
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
    public Product updateProduct(Product product) {
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

    @Override
    public Optional<Product> getProductById(Long id) {
        return productCRUDRepository.findById(id);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryCRUDRepository.findById(id);
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandCRUDRepository.findById(id);
    }

    @Override
    public Optional<MeasurementUnit> getMeasureUnitById(Long id) {
        return measurementUnitCRUDRepository.findById(id);
    }

    @Override
    public ProductImage createProductImage(ProductImage productImage) {
        return productImageCRUDRepository.save(productImage);
    }

    @Override
    public Page<Product> getAllProductPaginateAndSort(Specification<Product> specs, Pageable pageable) {
        return productCRUDRepository.findAll(specs, pageable);
    }

}
