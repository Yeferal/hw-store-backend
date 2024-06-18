package com.microservice.admin_microservice.domain.map;

import com.microservice.admin_microservice.domain.dto.AccountDTO;
import com.microservice.admin_microservice.persistence.model.Account;
import com.microservice.admin_microservice.persistence.model.UserProfile;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toEntity(AccountDTO accountDTO);

    @AfterMapping
    default void linkAssignmentRoleList(@MappingTarget Account account) {
        account.getAssignmentRoleList().forEach(assignmentRoleList -> assignmentRoleList.setAccount(account));
    }

    @AfterMapping
    default void linkUserProfile(@MappingTarget Account account) {
        UserProfile userProfile = account.getUserProfile();
        if (userProfile != null) {
            userProfile.setAccount(account);
        }
    }

    AccountDTO toDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountDTO accountDTO, @MappingTarget Account account);
}