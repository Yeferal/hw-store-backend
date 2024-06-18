package com.microservice.admin_microservice.domain.repository;

import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.AssignmentRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AccountRepository {

    Account createAccount(Account account);
    Boolean isExistUsername(String username);
    AssignmentRole createAssignmentRole(AssignmentRole assignmentRole);
    Page<Account> getAllAccountsPaginateAndSort(Specification<Account> specs, Pageable pageable);
    List<AssignmentRole> getAssignmentRoles();

}
