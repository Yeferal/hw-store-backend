package com.microservice.admin_microservice.persistence.crud;

import com.microservice.admin_microservice.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AccountCRUDRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

  Optional<Account> findByUsernameAndActiveTrue(String username);
  Optional<Account> findById(Long id);
  boolean existsByUsername(String username);
  void deleteById(Long id);
  void deleteAccountByUsername(String username);

}