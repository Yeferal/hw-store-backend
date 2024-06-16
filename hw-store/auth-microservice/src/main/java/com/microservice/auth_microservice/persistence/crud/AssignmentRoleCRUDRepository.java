package com.microservice.auth_microservice.persistence.crud;

import com.microservice.auth_microservice.persistence.model.AssignmentRole;
import com.microservice.auth_microservice.persistence.model.composite.AssignmentRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRoleCRUDRepository extends JpaRepository<AssignmentRole, AssignmentRoleId> {
    List<AssignmentRole> findByAccount_Id(Long id);
}