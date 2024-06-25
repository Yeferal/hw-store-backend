package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.AssignmentMeasure;
import com.microservice.sales_microservice.persistence.model.composite.AssignmentMeasureId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentMeasureCRUDRepository extends JpaRepository<AssignmentMeasure, AssignmentMeasureId> {
//    Optional<AssignmentMeasure> findByProductIdAndIsBase(Long productId);
    Optional<AssignmentMeasure> findByProductIdAndIsBase(Long productId, Boolean isBase);
}