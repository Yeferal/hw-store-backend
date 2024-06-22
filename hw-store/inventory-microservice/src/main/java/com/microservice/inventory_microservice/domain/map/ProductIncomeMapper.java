package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.ProductIncomeDTO;
import com.microservice.inventory_microservice.persistence.model.ProductIncome;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductIncomeMapper {
    ProductIncome toEntity(ProductIncomeDTO productIncomeDTO);

    @AfterMapping
    default void linkPurchases(@MappingTarget ProductIncome productIncome) {
        productIncome.getPurchases().forEach(purchase -> purchase.setProductIncome(productIncome));
    }

    ProductIncomeDTO toDto(ProductIncome productIncome);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductIncome partialUpdate(ProductIncomeDTO productIncomeDTO, @MappingTarget ProductIncome productIncome);
}