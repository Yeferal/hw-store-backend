package com.microservice.admin_microservice.domain.map;

import com.microservice.admin_microservice.domain.dto.AssignmentRoleDTO;
import com.microservice.admin_microservice.persistence.model.AssignmentRole;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssignmentRoleMapper {
    AssignmentRole toEntity(AssignmentRoleDTO assignmentRoleDTO);

    AssignmentRoleDTO toDto(AssignmentRole assignmentRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AssignmentRole partialUpdate(AssignmentRoleDTO assignmentRoleDTO, @MappingTarget AssignmentRole assignmentRole);
}