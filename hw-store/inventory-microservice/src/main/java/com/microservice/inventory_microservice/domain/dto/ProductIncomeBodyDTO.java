package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Value
public class ProductIncomeBodyDTO implements Serializable {
    @NotNull(message = "El campo date no puede ser Nulo")
    LocalDate date;
    @NotNull(message = "El campo supplierId no puede ser Nulo")
    Long supplierId;
    @NotNull(message = "El campo purchases no puede ser Nulo")
    List<PurchaseBodyDTO> purchases;
}
