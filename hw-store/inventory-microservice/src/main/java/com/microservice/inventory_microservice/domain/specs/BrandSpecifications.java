package com.microservice.inventory_microservice.domain.specs;

import com.microservice.inventory_microservice.persistence.model.Brand;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecifications {

    public static Specification<Brand> idContains(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("id")), "%" + id + "%");
    }

    public static Specification<Brand> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%");
    }

    public static Specification<Brand> isActive(Boolean active) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), active);
    }

    public static Specification<Brand> abbreviationContains(String abbreviation) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("abbreviation")), "%" + abbreviation + "%");
    }
}
