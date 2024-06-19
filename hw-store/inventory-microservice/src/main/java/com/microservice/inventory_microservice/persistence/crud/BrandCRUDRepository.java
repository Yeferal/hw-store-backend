package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandCRUDRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String brandName);
}