package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Brand}
 */
@Data
@AllArgsConstructor
@Value
public class BrandDTO implements Serializable {
    @NotNull(message = "El campo id no puede ser nulo")
    Long id;
    @NotBlank(message = "El campo id no puede ser nulo")
    String name;
    String abbreviation;
    Boolean state;
    String imgPath;
}