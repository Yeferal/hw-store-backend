package com.microservice.auth_microservice.persistence.crud;

import com.microservice.auth_microservice.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountCRUDRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    Optional<Account> existsAccountByUsername(String username);
}