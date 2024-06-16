package com.microservice.admin_microservice.initializer;

import com.microservice.admin_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.admin_microservice.persistence.crud.AssignmentRoleCRUDRepository;
import com.microservice.admin_microservice.persistence.crud.RoleCRUDRepository;
import com.microservice.admin_microservice.persistence.crud.UserProfileCRUDRepository;
import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.AssignmentRole;
import com.microservice.admin_microservice.persistence.model.Role;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import com.microservice.admin_microservice.source.utils.RoleType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Component
public class DataInitializer {

//    @Autowired
//    private AccountCRUDRepository accountCRUDRepository;
//    @Autowired
//    private RoleCRUDRepository roleCRUDRepository;
//    @Autowired
//    private AssignmentRoleCRUDRepository assignmentRoleCRUDRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    @Transactional
//    public void initData() {
//        initRoles();
//        initUsers();
//    }
//
//    private void initRoles(){
//        if(roleCRUDRepository.count() == 0){
//            roleCRUDRepository.save(new Role(RoleType.ADMIN.name(), "Administador"));
//            roleCRUDRepository.save(new Role(RoleType.OPERATOR.name(), "Operador"));
//            roleCRUDRepository.save(new Role(RoleType.RECEPTIONIST.name(), "Recepcionista"));
//            roleCRUDRepository.save(new Role(RoleType.CLIENT.name(), "Cliente"));
//        }
//    }
//
//    private void initUsers(){
//        if(accountCRUDRepository.count() == 0){
//            Account account = Account.builder()
//                    .username("Admin")
//                    .creationDate(LocalDateTime.now())
//                    .password(passwordEncoder.encode("password"))
//                    .verified(true)
//                    .active(true)
//                    .build();
//
//            Account account2 = Account.builder()
//                    .username("recep")
//                    .creationDate(LocalDateTime.now())
//                    .password(passwordEncoder.encode("password"))
//                    .verified(true)
//                    .active(true)
//                    .build();
//
//            UserProfile userProfile = UserProfile.builder()
//                    .firstName("NAME1")
//                    .lastName("LAST1")
//                    .account(account)
//                    .build();
//
//            UserProfile userProfile2 = UserProfile.builder()
//                    .firstName("RecepName")
//                    .lastName("LAST_RECEP")
//                    .account(account2)
//                    .build();
//
//            account.setUserProfile(userProfile);
//            account2.setUserProfile(userProfile2);
//
//            account = accountCRUDRepository.save(account);
//            account2 = accountCRUDRepository.save(account2);
//
//            Role roleAdmin = roleCRUDRepository.findByName(RoleType.ADMIN.name()).orElseThrow(() -> new RuntimeException("Role not found"));;
//            Role roleOperator = roleCRUDRepository.findByName(RoleType.OPERATOR.name()).orElseThrow(() -> new RuntimeException("Role not found"));;
//            Role roleReceptionist = roleCRUDRepository.findByName(RoleType.RECEPTIONIST.name()).orElseThrow(() -> new RuntimeException("Role not found"));;
//
//            AssignmentRole assignmentRole = AssignmentRole.builder()
//                    .accountId(account.getId())
//                    .roleId(roleAdmin.getId())
//                    .build();
//
//            AssignmentRole assignmentRole2 = AssignmentRole.builder()
//                    .accountId(account.getId())
//                    .roleId(roleOperator.getId())
//                    .build();
//
//            AssignmentRole assignmentRole3 = AssignmentRole.builder()
//                    .accountId(account2.getId())
//                    .roleId(roleReceptionist.getId())
//                    .build();
//
//            assignmentRoleCRUDRepository.save(assignmentRole);
//            assignmentRoleCRUDRepository.save(assignmentRole2);
//            assignmentRoleCRUDRepository.save(assignmentRole3);
//        }
//
//    }
}
