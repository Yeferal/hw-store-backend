package com.microservice.auth_microservice.domain.service;

import com.microservice.auth_microservice.domain.dto.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO authenticate(String username, String password);
    boolean logoutUser(String token, String sid);
    public boolean isLogging(String token, String sid);

}
