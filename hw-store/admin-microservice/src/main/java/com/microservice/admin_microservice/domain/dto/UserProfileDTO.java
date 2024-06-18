package com.microservice.admin_microservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.microservice.admin_microservice.persistence.model.UserProfile}
 */
@Data
@AllArgsConstructor
@Value
public class UserProfileDTO implements Serializable {
    Long accountId;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String phoneNumber;
    String email;
}