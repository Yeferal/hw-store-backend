package com.microservice.inventory_microservice.domain.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.AssignmentMeasure}
 */
@Value
public class AssignmentMeasureDTO implements Serializable {
    Long productId;
    Long measurementUnitId;
    BigDecimal equivalentValue;
    BigDecimal price;
    Boolean isBase;
    MeasurementUnitDTO measurementUnit;
}