package com.microservice.sales_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Value
public class SaleProductBodyDTO {
    @NotNull(message = "El campo productId no puede ser Nulo")
    Long productId;
    @NotNull(message = "El campo measurementUnitId no puede ser Nulo")
    Long measurementUnitId;
    @NotNull(message = "El campo amount no puede ser Nulo")
    BigDecimal amount;
    @NotNull(message = "El campo unitPrice no puede ser Nulo")
    BigDecimal unitPrice;
    @NotNull(message = "El campo subTotal no puede ser Nulo")
    BigDecimal subTotal;
    String description;
}
