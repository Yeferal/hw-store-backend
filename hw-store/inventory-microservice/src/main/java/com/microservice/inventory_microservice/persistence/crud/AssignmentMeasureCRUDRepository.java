package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.AssignmentMeasure;
import com.microservice.inventory_microservice.persistence.model.composite.AssignmentCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentMeasureCRUDRepository extends JpaRepository<AssignmentMeasure, AssignmentCategoryId> {
}