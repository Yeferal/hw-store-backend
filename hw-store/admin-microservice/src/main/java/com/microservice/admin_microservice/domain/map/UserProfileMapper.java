package com.microservice.admin_microservice.domain.map;

import com.microservice.admin_microservice.domain.dto.UserProfileDTO;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {
    UserProfile toEntity(UserProfileDTO userProfileDTO);

    UserProfileDTO toDto(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile partialUpdate(UserProfileDTO userProfileDTO, @MappingTarget UserProfile userProfile);
}