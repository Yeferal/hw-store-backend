package com.microservice.inventory_microservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.ProductIncome}
 */
@Data
@AllArgsConstructor
@Value
public class ProductIncomeDTO implements Serializable {
    @NotNull(message = "El campo id no puede ser Nulo")
    Long id;
    LocalDate date;
    BigDecimal total;
    SupplierDTO supplier;
    AccountMinimalDTO remitter;
    List<PurchaseDTO> purchases;
}