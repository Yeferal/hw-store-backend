package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCRUDRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}