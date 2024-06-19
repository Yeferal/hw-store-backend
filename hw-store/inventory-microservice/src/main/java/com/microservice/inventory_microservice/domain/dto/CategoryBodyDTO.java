package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Value
public class CategoryBodyDTO {

    @NotBlank(message = "El campo name es incorrecto")
    String name;

    String description;

    Boolean featured;   // Si la categoria es destacada o no

    Boolean state;   // Si la categoria es activa o no

    MultipartFile image;

}
