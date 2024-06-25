package com.microservice.sales_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Value
public class SaleBodyDTO {
    @NotNull(message = "El campo nit no puede ser Nulo")
    String nit;
    @NotNull(message = "El campo date no puede ser Nulo")
    LocalDate date;
    @NotNull(message = "El campo name no puede ser Nulo")
    String name;
    String phone;
    String address;
    String comment;
    @NotNull(message = "El campo saleProducts no puede ser Nulo")
    List<SaleProductBodyDTO> saleProducts;
}
