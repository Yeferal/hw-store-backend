package com.microservice.inventory_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.AssignmentCategory}
 */
@Data
@AllArgsConstructor
@Value
public class AssignmentCategoryDTO implements Serializable {
    Long productId;
    Long categoryId;
    CategoryDTO category;
}