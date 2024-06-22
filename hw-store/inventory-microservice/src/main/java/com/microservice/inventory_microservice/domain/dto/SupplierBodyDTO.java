package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Supplier}
 */
@Data
@AllArgsConstructor
@Value
public class SupplierBodyDTO implements Serializable {
    @NotBlank(message = "El campo name no es valido")
    String name;
    String address;
    String tel1;
    String tel2;
}