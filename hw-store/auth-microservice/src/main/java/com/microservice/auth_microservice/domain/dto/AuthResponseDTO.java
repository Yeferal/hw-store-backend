package com.microservice.auth_microservice.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@Value
public class AuthResponseDTO implements Serializable {

    private String accessToken;
    private String sid;
}
