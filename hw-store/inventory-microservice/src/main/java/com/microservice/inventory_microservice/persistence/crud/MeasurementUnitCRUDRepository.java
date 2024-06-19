package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementUnitCRUDRepository extends JpaRepository<MeasurementUnit, Long> {
    boolean existsByName(String name);
}