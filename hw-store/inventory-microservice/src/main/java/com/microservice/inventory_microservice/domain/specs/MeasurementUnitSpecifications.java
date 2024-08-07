package com.microservice.inventory_microservice.domain.specs;

import com.microservice.inventory_microservice.persistence.model.MeasurementUnit;
import org.springframework.data.jpa.domain.Specification;

public class MeasurementUnitSpecifications {

    public static Specification<MeasurementUnit> idContains(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("id")), "%" + id + "%");
    }

    public static Specification<MeasurementUnit> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%");
    }

    public static Specification<MeasurementUnit> symbolContains(String symbol) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("symbol")), "%" + symbol + "%");
    }

    public static Specification<MeasurementUnit> abbreviationContains(String abbreviation) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("abbreviation")), "%" + abbreviation + "%");
    }

    public static Specification<MeasurementUnit> magnitudeContains(String magnitude) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("magnitude")), "%" + magnitude + "%");
    }

}
