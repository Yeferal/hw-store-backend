package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.SupplierDTO;
import com.microservice.inventory_microservice.persistence.model.Supplier;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDTO supplierDTO);

    SupplierDTO toDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDTO supplierDTO, @MappingTarget Supplier supplier);
}