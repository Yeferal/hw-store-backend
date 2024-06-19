package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.MeasurementUnitDTO;
import com.microservice.inventory_microservice.persistence.model.MeasurementUnit;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasurementUnitMapper {
    MeasurementUnit toEntity(MeasurementUnitDTO measurementUnitDTO);

    MeasurementUnitDTO toDto(MeasurementUnit measurementUnit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MeasurementUnit partialUpdate(MeasurementUnitDTO measurementUnitDTO, @MappingTarget MeasurementUnit measurementUnit);
}