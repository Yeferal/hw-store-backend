package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Category}
 */
@Data
@AllArgsConstructor
@Value
public class CategoryDTO implements Serializable {
    @NotNull(message = "El campo id no puede ser nulo")
    Long id;
    @NotBlank(message = "El campo name no es correcto")
    String name;
    String description;
    Boolean featured;
    Boolean state;
    String imgPath;
}