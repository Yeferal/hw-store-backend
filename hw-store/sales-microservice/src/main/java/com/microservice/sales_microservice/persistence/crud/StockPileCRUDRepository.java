package com.microservice.sales_microservice.persistence.crud;

import com.microservice.sales_microservice.persistence.model.StockPile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface StockPileCRUDRepository extends JpaRepository<StockPile, Long> {

    @Query("SELECT SUM(sp.amount) FROM StockPile sp WHERE sp.product.id = :productId AND sp.active=true")
    BigDecimal sumAmountProductIdAndActive(Long productId);
    Optional<StockPile> findFirstByProductIdAndActiveTrueOrderByIdAsc(Long productId);
}