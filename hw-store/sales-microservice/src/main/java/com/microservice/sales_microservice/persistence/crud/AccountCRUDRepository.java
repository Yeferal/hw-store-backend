package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountCRUDRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}