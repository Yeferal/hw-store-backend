package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.StockPile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPileCRUDRepository extends JpaRepository<StockPile, Long> {
}