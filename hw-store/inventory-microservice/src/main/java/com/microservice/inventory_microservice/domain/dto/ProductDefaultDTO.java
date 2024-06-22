package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Product}
 */
@Data
@AllArgsConstructor
@Value
public class ProductDefaultDTO implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String code;
    @NotBlank
    String name;
    String description;
    BigDecimal retailPrice;
    BigDecimal wholesalePrice;
    BigDecimal previous_price;
    BigDecimal discount;
    String discountType;
    BigDecimal amount;
    BigDecimal minAmount;
    BigDecimal purchasePrice;
    Boolean delivery;
    BigDecimal deliveryPrice;
    Boolean formula;
    Boolean active;
    LocalDateTime creationDate;
    AccountMinimalDTO creator;
    List<ProductImageDTO> images;
    BrandDTO brand;
    List<AssignmentMeasureDTO> assignmentMeasureList;
    List<AssignmentCategoryDTO> assignmentCategoryList;
}