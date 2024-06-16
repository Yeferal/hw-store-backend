package com.microservice.auth_microservice.persistence.prepository;

import com.microservice.auth_microservice.domain.repository.AccountRepository;
import com.microservice.auth_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.AssignmentRoleCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.RoleCRUDRepository;
import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.AssignmentRole;
import com.microservice.auth_microservice.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Repository
public class AccountPRepository implements AccountRepository {

    @Autowired
    private AccountCRUDRepository accountCRUDRepository;
    @Autowired
    private RoleCRUDRepository roleCRUDRepository;
    @Autowired
    private AssignmentRoleCRUDRepository assignmentRoleCRUDRepository;

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return accountCRUDRepository.findByUsername(username);
    }

    @Override
    public List<Role> getAccountRoles(Long accountId) {
        List<AssignmentRole> assignmentRoleList = assignmentRoleCRUDRepository.findByAccount_Id(accountId);

        // Convertir la lista a un stream y mapear a un stream de nombres de roles
//        Stream<String> roleNameUserList = assignmentRoleList.stream()
//                .map(assignmentRole -> assignmentRole.getRole().getName());

        // Realizar operaciones con el nuevo stream de nombres de roles
//        roleNameUserList.forEach(System.out::println);

        List<Role> roleList = assignmentRoleList.stream()
//                .map(assignmentRole -> assignmentRole.getRole())
                .map(AssignmentRole::getRole)
                .toList();

        return roleList;
    }

}
