package com.microservice.sales_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sale_sub_details")
public class SaleSubDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal amount;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "unit_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal unitPrice; // Precio unitario al que se vendio

    @Column(name = "purchase_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal pruchasePrice; // Precio de compra unitario

    @Column(name = "sale_price", nullable = true, precision = 20, scale = 2)
    private BigDecimal salePrice; // SubTotal al que se vendio

    @Column(name = "subtotal", nullable = false, precision = 20, scale = 2)
    private BigDecimal subtotal; // Subtotal al que se compro

    @Column(name = "first_week", nullable = false)
    @ColumnDefault("false")
    private Boolean firstWeek;

    @Column(name = "tithe", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal tithe;

    @ManyToOne
    @JoinColumn(name = "sale_detail_id")
    private SaleDetail saleDetail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

}