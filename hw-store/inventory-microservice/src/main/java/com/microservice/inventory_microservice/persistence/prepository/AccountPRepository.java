package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.repository.AccountRepository;
import com.microservice.inventory_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.inventory_microservice.persistence.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountPRepository implements AccountRepository {

    @Autowired
    private AccountCRUDRepository accountCRUDRepository;

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountCRUDRepository.findById(id);
    }

    @Override
    public  Optional<Account> getAccountByUsername(String username) {
        return accountCRUDRepository.findByUsername(username);
    }
}
