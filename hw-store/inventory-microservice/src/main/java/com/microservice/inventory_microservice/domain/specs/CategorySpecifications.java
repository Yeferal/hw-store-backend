package com.microservice.inventory_microservice.domain.specs;

import com.microservice.inventory_microservice.persistence.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {

    public static Specification<Category> idContains(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("id")), "%" + id + "%");
    }

    public static Specification<Category> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%");
    }

    public static Specification<Category> isFeatured(Boolean featured) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("featured"), featured);
    }

    public static Specification<Category> isActive(Boolean active) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), active);
    }

    public static Specification<Category> abbreviationContains(String abbreviation) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("abbreviation")), "%" + abbreviation + "%");
    }

}
