package com.microservice.auth_microservice.initializer;

import com.microservice.auth_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.AssignmentRoleCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.RoleCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.UserProfileCRUDRepository;
import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.AssignmentRole;
import com.microservice.auth_microservice.persistence.model.Role;
import com.microservice.auth_microservice.persistence.model.UserProfile;
import com.microservice.auth_microservice.source.utils.RoleType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class DataInitializer {
    @Autowired
    private AccountCRUDRepository accountCRUDRepository;
    @Autowired
    private UserProfileCRUDRepository userProfileCRUDRepository;
    @Autowired
    private RoleCRUDRepository roleCRUDRepository;
    @Autowired
    private AssignmentRoleCRUDRepository assignmentRoleCRUDRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void initData() {
//        if (accountCRUDRepository.count() == 0) {
            System.out.println("SALUDOS...HUMANO");

//            Account account1 = Account.builder()
//                    .username("admin")
//                    .creationDate(LocalDateTime.now())
//                    .password(passwordEncoder.encode("admin"))
//                    .verified(true)
//                    .active(true)
//                    .build();
//
//            // Guardar la cuenta primero
//            account1 = accountCRUDRepository.save(account1);
//
//            // Crear el perfil de usuario y asociarlo a la cuenta
//            UserProfile userProfile1 = UserProfile.builder()
////                    .id(account1.getId())
//                    .accountId(account1.getId())
//                    .firstName("NAME1")
//                    .lastName("LAST1")
//                    .account(account1)
//                    .build();
//
//            // Establecer la relación bidireccional
//            account1.setUserProfile(userProfile1);
//
//            // Guardar el perfil de usuario
//            userProfile1 = userProfileCRUDRepository.save(userProfile1);
//
//            // Obteniendo roles
//            Role roleAdmin = roleCRUDRepository.findByName(RoleType.ADMIN.name()).get();
//            Role roleOperator = roleCRUDRepository.findByName(RoleType.OPERATOR.name()).get();
//            Role roleReceptionist = roleCRUDRepository.findByName(RoleType.RECEPTIONIST.name()).get();
//
//            AssignmentRole assignmentRole1 = AssignmentRole.builder()
//                    .accountId(account1.getId())
//                    .roleId(roleAdmin.getId())
////                    .account(account1)
////                    .role(roleAdmin)
//                    .build();

//            assignmentRoleCRUDRepository.save(assignmentRole1);
//        }
    }
}
