package com.microservice.auth_microservice.persistence.crud;

import com.microservice.auth_microservice.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleCRUDRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}