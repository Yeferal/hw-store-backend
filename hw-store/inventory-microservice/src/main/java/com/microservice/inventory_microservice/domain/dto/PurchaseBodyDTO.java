package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Value
public class PurchaseBodyDTO implements Serializable {
    @NotNull(message = "El campo productId no puede ser Nulo")
    Long productId;
    @NotNull(message = "El campo amount no puede ser Nulo")
    BigDecimal amount;
    @NotNull(message = "El campo unitPrice no puede ser Nulo")
    BigDecimal unitPrice;
    String description;
}