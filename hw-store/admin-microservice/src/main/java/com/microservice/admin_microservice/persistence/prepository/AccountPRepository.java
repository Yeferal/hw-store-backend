package com.microservice.admin_microservice.persistence.prepository;

import com.microservice.admin_microservice.domain.repository.AccountRepository;
import com.microservice.admin_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import com.microservice.admin_microservice.persistence.model.composite.AssignmentRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class AccountPRepository implements AccountRepository {

    @Autowired
    private AccountCRUDRepository accountCRUDRepository;

}
