package com.microservice.inventory_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.AssignmentMeasure}
 */
@Data
@AllArgsConstructor
@Value
public class AssignmentMeasureDTO implements Serializable {
    Long productId;
    Long measurementUnitId;
    Boolean isBase;
    MeasurementUnitDTO measurementUnit;
}