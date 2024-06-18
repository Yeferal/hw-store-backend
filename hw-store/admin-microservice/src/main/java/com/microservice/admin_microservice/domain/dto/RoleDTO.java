package com.microservice.admin_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.admin_microservice.persistence.model.Role}
 */
@Data
@AllArgsConstructor
@Value
public class RoleDTO implements Serializable {
    Long id;
    String name;
    String description;
}