package com.microservice.admin_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.admin_microservice.persistence.model.AssignmentRole}
 */
@Data
@AllArgsConstructor
@Value
public class AssignmentRoleDTO implements Serializable {
    Long accountId;
    Long roleId;
    RoleDTO role;
}