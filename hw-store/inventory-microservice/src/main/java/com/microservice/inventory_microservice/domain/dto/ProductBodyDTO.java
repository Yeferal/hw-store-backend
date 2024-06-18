package com.microservice.inventory_microservice.domain.dto;

import com.microservice.inventory_microservice.persistence.model.ProductImage;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Value
public class ProductBodyDTO implements Serializable {
    @NotBlank(message = "")
    String code;

    @NotBlank(message = "")
    String name;

    String description;

    BigDecimal retailPrice;

    BigDecimal wholesalePrice;

    BigDecimal previous_price;

    BigDecimal discount;

    String discountType;

    BigDecimal amount;

    BigDecimal minAmount;

    BigDecimal purchasePrice;

    Boolean delivery;

    BigDecimal deliveryPrice;

    Boolean formula;

    Long accountId;

    List<String> images;

    Long brandId;

    Long measurementUnitId;
}