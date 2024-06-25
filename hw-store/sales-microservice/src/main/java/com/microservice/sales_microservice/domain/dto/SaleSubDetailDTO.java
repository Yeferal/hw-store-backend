package com.microservice.sales_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.SaleSubDetail}
 */
@Data
@AllArgsConstructor
@Value
public class SaleSubDetailDTO implements Serializable {
    @NotNull(message = "El campo id no puede ser Nulo")
    Long id;
    BigDecimal amount;
    String description;
    BigDecimal unitPrice;
    BigDecimal pruchasePrice;
    BigDecimal salePrice;
    BigDecimal subtotal;
    Boolean firstWeek;
    BigDecimal tithe;
    ProductDTO product;
    MeasurementUnitDTO measurementUnit;
}