package com.microservice.sales_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.AssignmentMeasure}
 */
@Data
@AllArgsConstructor
@Value
public class AssignmentMeasureDTO implements Serializable {
    Long productId;
    Long measurementUnitId;
    BigDecimal equivalentValue;
    BigDecimal price;
    Boolean isBase;
    MeasurementUnitDTO measurementUnit;
}