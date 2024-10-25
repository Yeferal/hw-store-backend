package com.microservice.sales_microservice.domain.serviceimpl;

import com.microservice.sales_microservice.domain.dto.SaleBodyDTO;
import com.microservice.sales_microservice.domain.dto.SaleDTO;
import com.microservice.sales_microservice.domain.dto.SaleProductBodyDTO;
import com.microservice.sales_microservice.domain.map.SaleMapper;
import com.microservice.sales_microservice.domain.repository.ProductRepository;
import com.microservice.sales_microservice.domain.repository.SalesRepository;
import com.microservice.sales_microservice.domain.service.SalesService;
import com.microservice.sales_microservice.persistence.model.*;
import com.microservice.sales_microservice.persistence.model.composite.AssignmentMeasureId;
import com.microservice.sales_microservice.source.exception.FailedRegisterException;
import com.microservice.sales_microservice.source.exception.RegisterNotFoundException;
import com.microservice.sales_microservice.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SaleMapper saleMapper;

    @Override
    @Transactional(rollbackFor = {FailedRegisterException.class, Exception.class})
    public SaleDTO createSale(SaleBodyDTO saleBodyDTO, String token) {
        try {
            String username = jwtTokenProvider.getUsernameJwt(token);
            Account account = salesRepository.getAccountByUsername(username).orElseThrow(
                    () -> new RegisterNotFoundException("Account with username <"+ username +"> does not exist")
            );
            Client client = null;
            Optional<Client> clientOptional = salesRepository.getClientByNit(saleBodyDTO.getNit());
            if (clientOptional.isPresent()){
                client = clientOptional.get();
            } else {
                Client clientSave = Client.builder()
                        .nit(saleBodyDTO.getNit())
                        .name(saleBodyDTO.getName())
                        .address(saleBodyDTO.getAddress())
                        .phone(saleBodyDTO.getPhone())
                        .build();
                client = salesRepository.createClient(clientSave);
            }

            Sale sale = Sale.builder()
                    .name(saleBodyDTO.getName())
                    .address((saleBodyDTO.getAddress()==null)? "Paqui, Totonicapan":saleBodyDTO.getAddress())
                    .phone(saleBodyDTO.getPhone())
                    .date(LocalDateTime.of(saleBodyDTO.getDate(), LocalTime.from(LocalDateTime.now())))
                    .total(calculateTotal(saleBodyDTO.getSaleProducts()))
                    .adviser(account.getUsername())
                    .pendingPayment(saleBodyDTO.isPendingPayment())
                    .payment(saleBodyDTO.getPayment())
                    .comment(saleBodyDTO.getComment())
                    .client(client)
                    .account(account)
                    .build();

            sale = salesRepository.createSale(sale);
            addSaleDetail(sale, saleBodyDTO.getSaleProducts());

            return saleMapper.toDto(sale);
        } catch (Exception ex) {
            // La anotación @Transactional se encargará de realizar el rollback en caso de excepción.
            throw ex;
        }
    }

    private BigDecimal calculateTotal(List<SaleProductBodyDTO> saleProducts) {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleProductBodyDTO saleProduct : saleProducts) {
            total = total.add(saleProduct.getSubTotal());
        }
        return total;
    }

    private void addSaleDetail(Sale sale, List<SaleProductBodyDTO> saleProducts) {
        for(SaleProductBodyDTO saleProduct: saleProducts) {
            AssignmentMeasureId assignmentMeasureId = AssignmentMeasureId.builder()
                    .productId(saleProduct.getProductId())
                    .measurementUnitId(saleProduct.getMeasurementUnitId())
                    .build();

            AssignmentMeasure assignmentMeasure = salesRepository.getAssignmentMeasureById(assignmentMeasureId).orElseThrow(
                    () -> new RegisterNotFoundException("Assignment Measure <"+ assignmentMeasureId +"> does not exist")
            );

            Product product = assignmentMeasure.getProduct();
            BigDecimal unitPrice = saleProduct.getSubTotal().divide(saleProduct.getAmount(), 2, RoundingMode.HALF_UP);

            SaleDetail saleDetail  = SaleDetail.builder()
                    .amount(saleProduct.getAmount())
                    .description(product.getName())
                    .unitPrice(unitPrice)
                    .subtotal(saleProduct.getSubTotal())
                    .sale(sale)
                    .product(product)
                    .measurementUnit(assignmentMeasure.getMeasurementUnit())
                    .build();

            saleDetail = salesRepository.createSaleDetail(saleDetail);
            addSaleSubDetails(saleDetail, assignmentMeasure, saleProduct);

        }
    }

    private void addSaleSubDetails(SaleDetail saleDetail, AssignmentMeasure assignmentMeasure, SaleProductBodyDTO saleProduct) {
        // Amount exist
        BigDecimal amountStockPile = salesRepository.getAmountTotalStockPile(saleDetail.getProduct().getId());
        // Amount equivalent with measurement base
        BigDecimal amountEquivalent = saleDetail.getAmount().multiply(assignmentMeasure.getEquivalentValue());

        if (amountStockPile.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal amountPile = BigDecimal.ZERO;
            // Si existe en stock
            while ((amountEquivalent.compareTo(amountPile) > 0)){
                //GET FIRST STOCK PILE
                Optional<StockPile> firstStockPile = salesRepository.getFistStockPileActive(saleDetail.getProduct().getId());
                // IS EXIST SO CALCULATE
                if (firstStockPile.isPresent()) {
                    StockPile stockPile = firstStockPile.get();
                    BigDecimal purchasePriceBase = stockPile.getPurchasePrice();
                    BigDecimal unitPriceBase = saleDetail.getUnitPrice().divide(assignmentMeasure.getEquivalentValue(), 2, RoundingMode.HALF_UP);
                    BigDecimal difference = BigDecimal.ZERO;
                    BigDecimal pendingAmount = amountEquivalent.subtract(amountPile);

                    if (stockPile.getAmount().compareTo(pendingAmount) >= 0) {
                        // stock >= pendingAmount
                        difference = amountEquivalent.subtract(amountPile);
                    } else {
                        // pendingAmount > stock
                        difference = stockPile.getAmount();
                    }
                    amountPile = amountPile.add(difference);
                    BigDecimal newValAmountStock = stockPile.getAmount().subtract(difference);

                    if (newValAmountStock.compareTo(BigDecimal.ZERO) <= 0) {
                        stockPile.setAmount(BigDecimal.ZERO);
                        stockPile.setActive(false);
                    } else {
                        stockPile.setAmount(newValAmountStock);
                    }

                    generateFullSaleSubDetail(saleDetail, assignmentMeasure, difference, purchasePriceBase, unitPriceBase);
                    salesRepository.updateStockPile(stockPile);
                } else {
                    // IS NOT, SO GENERATE SALESUB ALSO ALL
                    BigDecimal difference = amountEquivalent.subtract(amountPile);
                    BigDecimal purchasePriceBase = assignmentMeasure.getProduct().getPurchasePrice();
                    BigDecimal unitPriceBase = saleDetail.getUnitPrice().divide(assignmentMeasure.getEquivalentValue(), 2, RoundingMode.HALF_UP);
                    generateFullSaleSubDetail(saleDetail, assignmentMeasure, difference, purchasePriceBase, unitPriceBase);
                    amountPile = amountEquivalent;
                }
            }
            // UPDATE STOCK
            BigDecimal totalAmount = salesRepository.getAmountTotalStockPile(saleDetail.getProduct().getId());
            productRepository.updateStock(saleDetail.getProduct().getId(), totalAmount);
        } else {
            // No hay en stock
            BigDecimal purchasePriceBase = assignmentMeasure.getProduct().getPurchasePrice();
            BigDecimal unitPriceBase = saleDetail.getUnitPrice().divide(assignmentMeasure.getEquivalentValue(), 2, RoundingMode.HALF_UP);
            generateFullSaleSubDetail(saleDetail, assignmentMeasure, amountEquivalent, purchasePriceBase, unitPriceBase);
            // UPDATE STOCK
            BigDecimal totalAmount = BigDecimal.ZERO;
            productRepository.updateStock(saleDetail.getProduct().getId(), totalAmount);
        }
    }

    private void generateFullSaleSubDetail(SaleDetail saleDetail, AssignmentMeasure assignmentMeasure, BigDecimal amountEquivalent, BigDecimal purchasePriceBase, BigDecimal unitPriceBase) {
        SaleSubDetail saleSubDetail = SaleSubDetail.builder()
                .description(saleDetail.getDescription())
                .amount(amountEquivalent)
                .unitPrice(unitPriceBase) // Precio unitario de venta
                .pruchasePrice(purchasePriceBase)  // Precio de compra
                .salePrice(saleDetail.getSubtotal()) // Precio al que se vendio
                .subtotal(purchasePriceBase.multiply(amountEquivalent)) // Subtotal de compra
                .firstWeek(false)
                .tithe(saleDetail.getSubtotal().multiply(BigDecimal.valueOf(0.10)).setScale(1, RoundingMode.HALF_UP))
                .saleDetail(saleDetail)
                .product(assignmentMeasure.getProduct())
                .measurementUnit(assignmentMeasure.getMeasurementUnit())
                .build();

        salesRepository.createSaleSubDetail(saleSubDetail);
    }

}
