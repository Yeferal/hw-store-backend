package com.microservice.auth_microservice.domain.repository;

import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.Role;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> getAccountByUsername(String username);
    List<Role> getAccountRoles(Long accountId);
}
