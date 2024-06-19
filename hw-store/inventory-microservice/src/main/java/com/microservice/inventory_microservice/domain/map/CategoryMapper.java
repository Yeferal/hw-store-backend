package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.CategoryDTO;
import com.microservice.inventory_microservice.persistence.model.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDTO categoryDTO, @MappingTarget Category category);
}