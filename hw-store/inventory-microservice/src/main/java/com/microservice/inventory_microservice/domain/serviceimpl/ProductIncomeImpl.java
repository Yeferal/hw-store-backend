package com.microservice.inventory_microservice.domain.serviceimpl;

import com.microservice.inventory_microservice.domain.dto.ProductIncomeBodyDTO;
import com.microservice.inventory_microservice.domain.dto.ProductIncomeDTO;
import com.microservice.inventory_microservice.domain.dto.PurchaseBodyDTO;
import com.microservice.inventory_microservice.domain.map.ProductIncomeMapper;
import com.microservice.inventory_microservice.domain.repository.*;
import com.microservice.inventory_microservice.domain.service.ProductIncomeService;
import com.microservice.inventory_microservice.persistence.model.*;
import com.microservice.inventory_microservice.source.exception.FailedRegisterException;
import com.microservice.inventory_microservice.source.exception.RegisterNotFoundException;
import com.microservice.inventory_microservice.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductIncomeImpl implements ProductIncomeService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductIncomeRepository productIncomeRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private StockPileRepository stockPileRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ProductIncomeMapper productIncomeMapper;

    @Override
    @Transactional(rollbackFor = {FailedRegisterException.class, Exception.class})
    public ProductIncomeDTO addIncomeProduct(ProductIncomeBodyDTO productIncomeBodyDTO, String token) {
        try {
            String username = jwtTokenProvider.getUsernameJwt(token);
            Account account = accountRepository.getAccountByUsername(username).orElseThrow(
                    () -> new RegisterNotFoundException("Account with username <"+ username+"> does not exist")
            );
            Supplier supplier = supplierRepository.getSupplierById(productIncomeBodyDTO.getSupplierId()).orElseThrow(
                    () -> new RegisterNotFoundException("Supplier with id <"+ productIncomeBodyDTO.getSupplierId() +"> does not exist")
            );

            // Crear Income
            ProductIncome productIncome = ProductIncome.builder()
                    .date(productIncomeBodyDTO.getDate())
                    .total(calculateTotalIncome(productIncomeBodyDTO.getPurchases()))
                    .supplier(supplier)
                    .remitter(account)
                    .build();
            productIncome = productIncomeRepository.createProductIncome(productIncome);

            if (productIncome == null) {
                throw new FailedRegisterException("Product Income not created");
            }

            List<Purchase> purchases = addPurchases(productIncome, productIncomeBodyDTO.getPurchases());
            addStocks(purchases);
            productIncome.setPurchases(purchases);

            return productIncomeMapper.toDto(productIncome);
        } catch (Exception ex) {
            // La anotación @Transactional se encargará de realizar el rollback en caso de excepción.
            throw ex;
        }
    }

    private BigDecimal calculateTotalIncome(List<PurchaseBodyDTO> purchaseBodyDTOList) {
        BigDecimal total = BigDecimal.valueOf(0);
        for (PurchaseBodyDTO purchaseBodyDTO : purchaseBodyDTOList) {
            BigDecimal priceAmountProduct = purchaseBodyDTO.getAmount().multiply(purchaseBodyDTO.getUnitPrice());
            total = total.add(priceAmountProduct);
        }
        return total;
    }

    private List<Purchase> addPurchases(ProductIncome productIncome, List<PurchaseBodyDTO> purchaseBodyDTOList) {
        List<Purchase> purchaseList = new ArrayList<>();
        for (PurchaseBodyDTO purchaseBodyDTO : purchaseBodyDTOList) {
            BigDecimal amount = purchaseBodyDTO.getAmount();
            BigDecimal unitPrice = purchaseBodyDTO.getUnitPrice();
            Product product = productRepository.getProductById(purchaseBodyDTO.getProductId()).orElseThrow(
                    () -> new RegisterNotFoundException("Product with id <"+ purchaseBodyDTO.getProductId() +"> does not exist")
            );

            product.setPurchasePrice(unitPrice);

            Purchase purchase = Purchase.builder()
                    .product(product)
                    .amount(amount)
                    .unitPrice(unitPrice)
                    .subtotal(amount.multiply(unitPrice))
                    .description(purchaseBodyDTO.getDescription())
                    .productIncome(productIncome)
                    .build();

            purchase = productIncomeRepository.createPurchase(purchase);
            productRepository.updateProduct(product);
            if (purchase == null) {
                throw new FailedRegisterException("Purchase failed");
            }
            purchaseList.add(purchase);
        }
        return purchaseList;
    }

    private void addStocks(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            StockPile stockPile = StockPile.builder()
                    .amount(purchase.getAmount())
                    .purchasePrice(purchase.getUnitPrice())
                    .active(true)
                    .purchase(purchase)
                    .product(Product.builder().id(purchase.getProduct().getId()).build())
                    .build();
            stockPile = productIncomeRepository.createStockPile(stockPile);
            if (stockPile == null) {
                throw new FailedRegisterException("StockPile failed");
            }

            // Actualizar stock del producto hacer conteo
            BigDecimal totalAmount = stockPileRepository.sumTotalStockPile(purchase.getProduct().getId());
            productRepository.updateStock(purchase.getProduct().getId(), totalAmount);
        }
    }

}
