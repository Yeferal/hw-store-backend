package com.microservice.sales_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.Product}
 */
@Data
@AllArgsConstructor
@Value
public class ProductDTO implements Serializable {
    Long id;
    String code;
    String name;
    BigDecimal retailPrice;
    BigDecimal wholesalePrice;
    BigDecimal previous_price;
    BigDecimal purchasePrice;
    Boolean delivery;
    BigDecimal deliveryPrice;
    Boolean formula;
    List<AssignmentMeasureDTO> assignmentMeasureList;
}