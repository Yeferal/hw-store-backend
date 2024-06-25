package com.microservice.sales_microservice.domain.repository;

import com.microservice.sales_microservice.persistence.model.*;
import com.microservice.sales_microservice.persistence.model.composite.AssignmentMeasureId;

import java.math.BigDecimal;
import java.util.Optional;

public interface SalesRepository {
    Optional<Account> getAccountByUsername(String username);
    Optional<Client> getClientByNit(String nit);
    Client createClient(Client client);
    Sale createSale(Sale sale);
    SaleDetail createSaleDetail(SaleDetail saleDetail);
    SaleSubDetail createSaleSubDetail(SaleSubDetail saleSubDetail);
    StockPile createStockPile(StockPile stockPile);
    StockPile updateStockPile(StockPile stockPile);
    Optional<AssignmentMeasure> getAssignmentMeasureById(AssignmentMeasureId id);
    Optional<AssignmentMeasure> getAssignmentMeasureByProducIdIsBaseTrue(Long productId);
    Optional<Product> geProductById(Long id);
    BigDecimal getAmountTotalStockPile(Long productId);
    Optional<StockPile> getFistStockPileActive(Long productId);
}
