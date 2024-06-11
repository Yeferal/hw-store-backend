package com.microservice.auth_microservice.persistence.prepository;

import com.microservice.auth_microservice.domain.repository.UserRepository;
import com.microservice.auth_microservice.persistence.crud.AccountCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.AssignmentRoleCRUDRepository;
import com.microservice.auth_microservice.persistence.crud.UserProfileCRUDRepository;
import com.microservice.auth_microservice.persistence.model.Account;
import com.microservice.auth_microservice.persistence.model.AssignmentRole;
import com.microservice.auth_microservice.persistence.model.UserProfile;
import com.microservice.auth_microservice.persistence.model.composite.AssignmentRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class UserPRepository implements UserRepository {

    @Autowired
    private AccountCRUDRepository accountCRUDRepository;
    @Autowired
    private AssignmentRoleCRUDRepository assignmentRoleCRUDRepository;
    @Autowired
    private UserProfileCRUDRepository userProfileCRUDRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


}
