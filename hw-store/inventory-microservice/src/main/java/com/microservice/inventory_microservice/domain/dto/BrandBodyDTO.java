package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Value
public class BrandBodyDTO {

    @NotBlank(message = "El campo name es incorrecto")
    String name;

    String abbreviation;

    Boolean state;   // Si es activa o no

    MultipartFile image;
}
