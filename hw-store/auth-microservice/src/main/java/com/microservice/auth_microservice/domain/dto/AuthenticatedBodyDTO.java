package com.microservice.auth_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

//@Getter
//@Setter
@Data
@Builder
@AllArgsConstructor
@Value
public class AuthenticatedBodyDTO implements Serializable {
    /**
     * @NotBlank: Valida que un campo no esté vacío y que contenga al menos un carácter no espaciado. No permite
     * espacios en blanco como único contenido válido.
     * @NotEmpty: Valida que un campo no esté vacío. Permite espacios en blanco como contenido válido, siempre y cuando
     * el campo no esté completamente vacío.
     * */

    @NotNull(message = "No puede ser Nullo")
    @NotBlank(message = "No puede contener espacios en blanco")
    @NotEmpty(message = "No puede ser vacio")
    String username;

    @NotNull(message = "No puede ser Nullo")
    @NotBlank(message = "No puede contener espacios en blanco")
    @NotEmpty(message = "No puede ser vacio")
    String password;
}
