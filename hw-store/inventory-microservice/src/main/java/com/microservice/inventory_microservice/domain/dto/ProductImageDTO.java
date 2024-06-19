package com.microservice.inventory_microservice.domain.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.ProductImage}
 */
@Value
public class ProductImageDTO implements Serializable {
    Long id;
    String imagePath;
}