package com.microservice.inventory_microservice.domain.repository;

import java.math.BigDecimal;


public interface StockPileRepository {

    BigDecimal sumTotalStockPile(Long productId);
}
