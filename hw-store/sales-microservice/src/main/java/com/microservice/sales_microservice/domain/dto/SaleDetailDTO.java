package com.microservice.sales_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.SaleDetail}
 */
@Data
@AllArgsConstructor
@Value
public class SaleDetailDTO implements Serializable {
    Long id;
    BigDecimal amount;
    String description;
    BigDecimal unitPrice;
    BigDecimal subtotal;
    ProductDTO product;
    MeasurementUnitDTO measurementUnit;
}