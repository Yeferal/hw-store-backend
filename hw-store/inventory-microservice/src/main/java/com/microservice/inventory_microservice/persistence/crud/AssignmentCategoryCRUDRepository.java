package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.AssignmentCategory;
import com.microservice.inventory_microservice.persistence.model.composite.AssignmentCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentCategoryCRUDRepository extends JpaRepository<AssignmentCategory, AssignmentCategoryId> {
}