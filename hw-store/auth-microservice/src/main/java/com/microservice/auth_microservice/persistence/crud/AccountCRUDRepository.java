package com.microservice.auth_microservice.persistence.crud;

import com.microservice.auth_microservice.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCRUDRepository extends JpaRepository<Account, Long> {
}