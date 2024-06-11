package com.microservice.admin_microservice.persistence.prepository;

import com.microservice.admin_microservice.domain.repository.RoleRepository;
import com.microservice.admin_microservice.persistence.crud.RoleCRUDRepository;
import com.microservice.admin_microservice.persistence.model.Role;
import com.microservice.admin_microservice.source.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolePRepository implements RoleRepository {

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

}
