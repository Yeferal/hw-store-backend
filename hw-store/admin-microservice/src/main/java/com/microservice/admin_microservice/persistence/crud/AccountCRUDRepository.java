package com.microservice.admin_microservice.persistence.crud;

import com.microservice.admin_microservice.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountCRUDRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByUsernameAndActiveTrue(String username);
  Optional<Account> findById(Long id);
  boolean existsByUsername(String username);
  void deleteById(Long id);
  void deleteAccountByUsername(String username);

}