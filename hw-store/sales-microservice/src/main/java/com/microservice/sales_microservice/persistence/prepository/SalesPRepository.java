package com.microservice.sales_microservice.persistence.prepository;

import com.microservice.sales_microservice.domain.repository.SalesRepository;
import com.microservice.sales_microservice.persistence.crud.*;
import com.microservice.sales_microservice.persistence.model.*;
import com.microservice.sales_microservice.persistence.model.composite.AssignmentMeasureId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class SalesPRepository implements SalesRepository {
    @Autowired
    private AccountCRUDRepository accountCRUDRepository;
    @Autowired
    private AssignmentMeasureCRUDRepository assignmentMeasureCRUDRepository;
    @Autowired
    private ClientCRUDRepository clientCRUDRepository;
    @Autowired
    private SaleCRUDRepository saleCRUDRepository;
    @Autowired
    private SaleDetailCRUDRepository saleDetailCRUDRepository;
    @Autowired
    private SaleSubDetailCRUDRepository SaleSubDetailCRUDRepository;
    @Autowired
    private StockPileCRUDRepository stockPileCRUDRepository;
    @Autowired
    private SaleSubDetailCRUDRepository saleSubDetailCRUDRepository;

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return accountCRUDRepository.findByUsername(username);
    }

    @Override
    public Optional<Client> getClientByNit(String nit) {
        return clientCRUDRepository.findByNit(nit);
    }

    @Override
    public Client createClient(Client client) {
        return clientCRUDRepository.save(client);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleCRUDRepository.save(sale);
    }

    @Override
    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        return saleDetailCRUDRepository.save(saleDetail);
    }

    @Override
    public SaleSubDetail createSaleSubDetail(SaleSubDetail saleSubDetail) {
        return saleSubDetailCRUDRepository.save(saleSubDetail);
    }

    @Override
    public StockPile createStockPile(StockPile stockPile) {
        return stockPileCRUDRepository.save(stockPile);
    }

    @Override
    public StockPile updateStockPile(StockPile stockPile) {
        return stockPileCRUDRepository.save(stockPile);
    }

    @Override
    public Optional<AssignmentMeasure> getAssignmentMeasureById(AssignmentMeasureId id) {
        return assignmentMeasureCRUDRepository.findById(id);
    }

    @Override
    public Optional<AssignmentMeasure> getAssignmentMeasureByProducIdIsBaseTrue(Long productId) {
        return assignmentMeasureCRUDRepository.findByProductIdAndIsBase(productId, true);
    }

    @Override
    public Optional<Product> geProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public BigDecimal getAmountTotalStockPile(Long productId) {
        BigDecimal amount = stockPileCRUDRepository.sumAmountProductIdAndActive(productId);
        if (amount == null)
            return BigDecimal.valueOf(0);
        return amount;
    }

    @Override
    public Optional<StockPile> getFistStockPileActive(Long productId) {
        return stockPileCRUDRepository.findFirstByProductIdAndActiveTrueOrderByIdAsc(productId);
    }
}
