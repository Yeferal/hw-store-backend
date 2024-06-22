package com.microservice.inventory_microservice.persistence.prepository;

import com.microservice.inventory_microservice.domain.repository.ProductIncomeRepository;
import com.microservice.inventory_microservice.persistence.crud.ProductIncomeCRUDRepository;
import com.microservice.inventory_microservice.persistence.crud.PurchaseCRUDRepository;
import com.microservice.inventory_microservice.persistence.crud.StockPileCRUDRepository;
import com.microservice.inventory_microservice.persistence.model.ProductIncome;
import com.microservice.inventory_microservice.persistence.model.Purchase;
import com.microservice.inventory_microservice.persistence.model.StockPile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductIncomePRepository implements ProductIncomeRepository {
    @Autowired
    private ProductIncomeCRUDRepository productIncomeCRUDRepository;
    @Autowired
    private PurchaseCRUDRepository purchaseCRUDRepository;
    @Autowired
    private StockPileCRUDRepository stockPileCRUDRepository;

    @Override
    public ProductIncome createProductIncome(ProductIncome incomeProduct) {
        return productIncomeCRUDRepository.save(incomeProduct);
    }

    @Override
    public Optional<ProductIncome> getProductIncomeById(Long id) {
        return productIncomeCRUDRepository.findById(id);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        return purchaseCRUDRepository.save(purchase);
    }

    @Override
    public StockPile createStockPile(StockPile stockPile) {
        return stockPileCRUDRepository.save(stockPile);
    }
}
