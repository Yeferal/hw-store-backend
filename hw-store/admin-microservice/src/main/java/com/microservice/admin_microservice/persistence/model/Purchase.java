package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal amount;

    @Column(name = "unit_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "subtotal", nullable = false, precision = 20, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "income_product_id")
    private IncomeProduct incomeProduct;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}