package com.microservice.sales_microservice.domain.map;

import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.persistence.model.Sale;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaleMapper {
    Sale toEntity(SaleDTO saleDTO);

    SaleDTO toDto(Sale sale);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sale partialUpdate(SaleDTO saleDTO, @MappingTarget Sale sale);
}