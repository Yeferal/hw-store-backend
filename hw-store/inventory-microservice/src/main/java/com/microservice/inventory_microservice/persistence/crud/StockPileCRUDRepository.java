package com.microservice.inventory_microservice.persistence.crud;

import com.microservice.inventory_microservice.persistence.model.StockPile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface StockPileCRUDRepository extends JpaRepository<StockPile, Long> {

    @Query("SELECT SUM(sp.amount) FROM StockPile sp WHERE sp.product.id = :productId AND sp.active=true")
    BigDecimal sumFindByProductId(@Param("productId") Long productId);
}