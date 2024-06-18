package com.microservice.auth_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.auth_microservice.persistence.model.Account}
 */
@Value
public class AccountDTO implements Serializable {
    @NotNull
    Long id;
    @NotNull(message = "El campo username no puede ser Nulo")
    @NotBlank(message = "El campo username no puede ser Vacio")
    String username;
    @NotNull
    Boolean verified;
    @NotNull
    Boolean active;
}