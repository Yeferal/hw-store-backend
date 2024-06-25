package com.microservice.sales_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.Sale}
 */
@Data
@AllArgsConstructor
@Value
public class SaleDTO implements Serializable {
    Long id;
    String name;
    String address;
    String phone;
    LocalDateTime date;
    BigDecimal total;
    String adviser;
    Boolean pendingPayment;
    BigDecimal payment;
    String comment;
    ClientDTO client;
}