package com.microservice.inventory_microservice.domain.repository;

import com.microservice.inventory_microservice.persistence.model.ProductIncome;
import com.microservice.inventory_microservice.persistence.model.Purchase;
import com.microservice.inventory_microservice.persistence.model.StockPile;

import java.util.Optional;

public interface ProductIncomeRepository {

    ProductIncome createProductIncome(ProductIncome incomeProduct);
    Optional<ProductIncome> getProductIncomeById(Long id);
    Purchase createPurchase(Purchase purchase);
    StockPile createStockPile(StockPile stockPile);
}
