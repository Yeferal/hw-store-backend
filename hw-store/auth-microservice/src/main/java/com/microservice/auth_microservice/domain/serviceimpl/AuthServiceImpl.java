package com.microservice.auth_microservice.domain.serviceimpl;

import com.microservice.auth_microservice.domain.dto.AuthResponseDTO;
import com.microservice.auth_microservice.domain.repository.AccountRepository;
import com.microservice.auth_microservice.domain.service.AuthService;
import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.Role;
import com.microservice.auth_microservice.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO authenticate(String username, String password) {
        Account account = accountRepository.getAccountByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " no encontrado")
        );
        if (account == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
        } else {
            if (verifyPassword(password, account.getPassword())) {
                // Obtener roles del usuario
                List<Role> roleList = accountRepository.getAccountRoles(account.getId());
                List<String> roleNameList = roleList.stream().map(Role::getName).toList();

                // Autenticar
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        username, password));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String browserId = UUID.randomUUID().toString();
                String token = jwtTokenProvider.generateToken(authentication, roleNameList, browserId);

                return AuthResponseDTO.builder()
                        .accessToken(token)
                        .sid(browserId)
                        .build();
            }
        }
        return null;
    }

    @Override
    public boolean logoutUser(String token, String sid) {
        String username = jwtTokenProvider.getUsernameJwt(token);
        return jwtTokenProvider.deleteToken(username, sid);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean isLogging(String token, String sid) {
        return jwtTokenProvider.validToken(token, sid);
    }
}
