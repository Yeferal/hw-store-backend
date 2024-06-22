package com.microservice.inventory_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.inventory_microservice.persistence.model.Supplier}
 */
@Data
@AllArgsConstructor
@Value
public class SupplierDTO implements Serializable {
    Long id;
    String name;
    String address;
    String tel1;
    String tel2;
}