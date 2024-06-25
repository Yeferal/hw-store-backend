package com.microservice.sales_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.sales_microservice.persistence.model.Client}
 */
@Data
@AllArgsConstructor
@Value
public class ClientDTO implements Serializable {
    Long id;
    String nit;
    String name;
    String address;
    String phone;
}