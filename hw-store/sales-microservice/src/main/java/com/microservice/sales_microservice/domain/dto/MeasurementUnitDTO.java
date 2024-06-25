package com.microservice.sales_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.MeasurementUnit}
 */
@Data
@AllArgsConstructor
@Value
public class MeasurementUnitDTO implements Serializable {
    @NotNull(message = "El campo id no pude ser Nulo")
    Long id;
    String name;
    String abbreviation;
    String symbol;
    String magnitude;
}