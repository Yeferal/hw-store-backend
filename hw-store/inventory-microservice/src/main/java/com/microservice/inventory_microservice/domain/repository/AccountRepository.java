package com.microservice.inventory_microservice.domain.repository;

import com.microservice.inventory_microservice.persistence.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getAccountById(Long id);
    Optional<Account> getAccountByUsername(String username);
}
