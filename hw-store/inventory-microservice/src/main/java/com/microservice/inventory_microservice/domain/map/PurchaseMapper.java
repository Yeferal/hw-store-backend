package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.PurchaseDTO;
import com.microservice.inventory_microservice.persistence.model.Purchase;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    Purchase toEntity(PurchaseDTO purchaseDTO);

    PurchaseDTO toDto(Purchase purchase);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Purchase partialUpdate(PurchaseDTO purchaseDTO, @MappingTarget Purchase purchase);
}