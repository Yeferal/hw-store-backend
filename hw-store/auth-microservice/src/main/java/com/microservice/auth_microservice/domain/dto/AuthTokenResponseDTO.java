package com.microservice.auth_microservice.domain.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenResponseDTO implements Serializable {
    private String accessToken;
}
