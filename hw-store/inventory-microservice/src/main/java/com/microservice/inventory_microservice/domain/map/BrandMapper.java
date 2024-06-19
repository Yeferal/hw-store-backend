package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.BrandDTO;
import com.microservice.inventory_microservice.persistence.model.Brand;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {
    Brand toEntity(BrandDTO brandDTO);

    BrandDTO toDto(Brand brand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Brand partialUpdate(BrandDTO brandDTO, @MappingTarget Brand brand);
}