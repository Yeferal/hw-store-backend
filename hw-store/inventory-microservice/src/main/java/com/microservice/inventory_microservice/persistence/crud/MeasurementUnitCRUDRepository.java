package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeasurementUnitCRUDRepository extends JpaRepository<MeasurementUnit, Long>, JpaSpecificationExecutor<MeasurementUnit> {
    boolean existsByName(String name);
}