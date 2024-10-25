package com.microservice.sales_microservice.domain.specs;

import com.microservice.sales_microservice.persistence.model.Sale;
import org.springframework.data.jpa.domain.Specification;

public class SaleSpecifications {

    public static Specification<Sale> idContains(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.toString(root.get("id")), "%" + id + "%");
    }

    public static Specification<Sale> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name + "%");
    }

    public static Specification<Sale> addressContains(String address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("address")), "%" + address + "%");
    }

    public static Specification<Sale> isPendingPayment(Boolean pendingPayment) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("pendingPayment"), pendingPayment);
    }
}
