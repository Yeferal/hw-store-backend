package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@Value
public class MeasurementUnitBodyDTO {

    @NotBlank(message = "El campo name es incorrecto")
    String name;

    String abbreviation;

    String symbol;

    @NotBlank(message = "El magnitude name es incorrecto")
    String magnitude;
}
