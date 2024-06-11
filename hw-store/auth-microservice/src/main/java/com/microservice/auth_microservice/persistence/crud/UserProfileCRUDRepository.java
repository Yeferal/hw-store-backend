package com.microservice.auth_microservice.persistence.crud;

import com.microservice.auth_microservice.persistence.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileCRUDRepository extends JpaRepository<UserProfile, Long> {
}