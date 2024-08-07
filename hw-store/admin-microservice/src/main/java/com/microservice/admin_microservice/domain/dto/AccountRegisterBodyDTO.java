package com.microservice.admin_microservice.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Value
public class AccountRegisterBodyDTO implements Serializable {

    @NotEmpty(message = "El campo username no puede ser Vacio")
    String username;

    @NotEmpty(message = "El campo password no puede ser Vacio")
    String password;

    @NotNull(message = "El campo roleId no puede ser Nulo")
    Long roleId;

    @NotEmpty(message = "El campo firstname no puede ser vacio")
    String firstname;

    @NotEmpty(message = "El campo lastname no puede ser vacio")
    String lastname;

    String email;
    String phoneNumber;
}