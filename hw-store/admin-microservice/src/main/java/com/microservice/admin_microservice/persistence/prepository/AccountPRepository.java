package com.microservice.admin_microservice.persistence.prepository;

import com.microservice.admin_microservice.domain.repository.AccountRepository;
import com.microservice.admin_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.admin_microservice.persistence.crud.AssignmentRoleCRUDRepository;
import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.AssignmentRole;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import com.microservice.admin_microservice.persistence.model.composite.AssignmentRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AccountPRepository implements AccountRepository {

    @Autowired
    private AccountCRUDRepository accountCRUDRepository;
    @Autowired
    private AssignmentRoleCRUDRepository assignmentRoleCRUDRepository;


    @Override
    public Account createAccount(Account account) {
        return accountCRUDRepository.save(account);
    }

    @Override
    public Boolean isExistUsername(String username) {
        return accountCRUDRepository.existsByUsername(username);
    }

    @Override
    public AssignmentRole createAssignmentRole(AssignmentRole assignmentRole) {
        return assignmentRoleCRUDRepository.save(assignmentRole);
    }

    @Override
    public Page<Account> getAllAccountsPaginateAndSort(Specification<Account> specs, Pageable pageable) {
        return accountCRUDRepository.findAll(specs, pageable);
    }

    @Override
    public List<AssignmentRole> getAssignmentRoles() {
        return List.of();
    }


}
