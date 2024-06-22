package com.microservice.inventory_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Purchase}
 */
@Data
@AllArgsConstructor
@Value
public class PurchaseDTO implements Serializable {
    Long id;
    BigDecimal amount;
    BigDecimal unitPrice;
    String description;
    BigDecimal subtotal;
    ProductDefaultDTO product;
}