package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageCRUDRepository extends JpaRepository<ProductImage, Long> {
}