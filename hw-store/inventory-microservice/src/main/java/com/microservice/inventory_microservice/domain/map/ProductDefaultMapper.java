package com.microservice.inventory_microservice.domain.map;

import com.microservice.inventory_microservice.domain.dto.ProductDefaultDTO;
import com.microservice.inventory_microservice.persistence.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductDefaultMapper {
    Product toEntity(ProductDefaultDTO productDefaultDTO);

    @AfterMapping
    default void linkImages(@MappingTarget Product product) {
        product.getImages().forEach(image -> image.setProduct(product));
    }

    @AfterMapping
    default void linkAssignmentMeasureList(@MappingTarget Product product) {
        product.getAssignmentMeasureList().forEach(assignmentMeasureList -> assignmentMeasureList.setProduct(product));
    }

    @AfterMapping
    default void linkAssignmentCategoryList(@MappingTarget Product product) {
        product.getAssignmentCategoryList().forEach(assignmentCategoryList -> assignmentCategoryList.setProduct(product));
    }

    ProductDefaultDTO toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDefaultDTO productDefaultDTO, @MappingTarget Product product);
}