package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.repository.StockPileRepository;
import com.microservice.inventory_microservice.persistence.crud.StockPileCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class StockPilePRepository implements StockPileRepository {

    @Autowired
    private StockPileCRUDRepository stockPileCRUDRepository;

    @Override
    public BigDecimal sumTotalStockPile(Long productId) {
        return stockPileCRUDRepository.sumFindByProductId(productId);
    }
}
