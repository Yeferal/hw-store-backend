package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.ProductMinimalDTO;
import com.microservice.inventory_microservice.persistence.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMinimalMapper {
    Product toEntity(ProductMinimalDTO productMinimalDTO);

    ProductMinimalDTO toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductMinimalDTO productMinimalDTO, @MappingTarget Product product);
}