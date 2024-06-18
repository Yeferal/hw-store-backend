package com.microservice.admin_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.microservice.admin_microservice.persistence.model.Account}
 */
@Data
@AllArgsConstructor
@Value
public class AccountDefaultDTO implements Serializable {
    Long id;
    String username;
    LocalDateTime creationDate;
    String password;
    Boolean verified;
    Boolean active;
    List<AssignmentRoleDTO> assignmentRoleList;
    UserProfileDTO userProfile;
}