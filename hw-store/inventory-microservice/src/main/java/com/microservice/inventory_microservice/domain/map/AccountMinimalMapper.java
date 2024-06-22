package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.AccountMinimalDTO;
import com.microservice.inventory_microservice.persistence.model.Account;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMinimalMapper {
    Account toEntity(AccountMinimalDTO accountMinimalDTO);

    AccountMinimalDTO toDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountMinimalDTO accountMinimalDTO, @MappingTarget Account account);
}