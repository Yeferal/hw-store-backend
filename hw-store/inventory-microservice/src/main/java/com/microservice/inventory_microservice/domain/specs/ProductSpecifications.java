package com.microservice.inventory_microservice.domain.specs;

import com.microservice.inventory_microservice.persistence.model.AssignmentCategory;
import com.microservice.inventory_microservice.persistence.model.Category;
import com.microservice.inventory_microservice.persistence.model.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    // PRODUCT
    public static Specification<Product> codeContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + name + "%");
    }

    public static Specification<Product> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%");
    }

    //BRAND
    public static Specification<Product> brandNameContains(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // Retorna una condición verdadera siempre
            }
            return criteriaBuilder.like(criteriaBuilder.upper(root.get("brand").get("name")), "%" + name.toLowerCase() + "%");
        });
    }

    // CATEGORY
    public static Specification<Product> categoryNameContains(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // Retorna una condición verdadera siempre
            }

            // Join con la lista assignmentCategoryList
            Join<Product, AssignmentCategory> assignmentCategoryJoin = root.join("assignmentCategoryList", JoinType.LEFT);
            // Join con la categoría
            Join<AssignmentCategory, Category> categoryJoin = assignmentCategoryJoin.join("category", JoinType.LEFT);
            // Aplicar el filtro en el nombre de la categoría
            return criteriaBuilder.like(criteriaBuilder.upper(categoryJoin.get("name")), "%" + name.toLowerCase() + "%");
        });
    }
}
