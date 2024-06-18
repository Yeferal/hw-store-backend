package com.microservice.admin_microservice.domain.repository;

import com.microservice.admin_microservice.persistence.model.UserProfile;

public interface UserProfileRepository {

    UserProfile createUserProfile(UserProfile userProfile);
}
