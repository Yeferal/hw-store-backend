package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Account}
 */
@Data
@AllArgsConstructor
@Value
public class AccountDTO implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String username;
    Boolean verified;
    Boolean active;
}