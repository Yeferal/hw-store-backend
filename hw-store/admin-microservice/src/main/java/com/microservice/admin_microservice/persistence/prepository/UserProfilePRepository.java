package com.microservice.admin_microservice.persistence.prepository;

import com.microservice.admin_microservice.domain.repository.UserProfileRepository;
import com.microservice.admin_microservice.persistence.crud.UserProfileCRUDRepository;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfilePRepository implements UserProfileRepository {

    @Autowired
    private UserProfileCRUDRepository userProfileCRUDRepository;


    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileCRUDRepository.save(userProfile);
    }
}
