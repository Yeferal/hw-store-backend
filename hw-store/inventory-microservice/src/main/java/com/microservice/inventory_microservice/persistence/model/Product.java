package com.microservice.inventory_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "retail_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal retailPrice; // Precio al Detal o minorista, o comsumidor

    @Column(name = "wholesale_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal wholesalePrice; // Precio al mayorista

    @Column(name = "previous_price", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal previousPrice; // Precio anterior

    @Column(name = "discount", precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal discount; // Descuento

    @Column(name = "discount_type ", nullable = true)
    private String discountType ; // Tipo de descuento (% o Fijo)

    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal amount;

    @Column(name = "min_amount", precision = 10, scale = 4)
    @ColumnDefault("0.00")
    private BigDecimal minAmount;

    @Column(name = "purchase_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "delivery")
    @ColumnDefault("false")
    private Boolean delivery; //Cambiarlo a los productos que son deliveri en una tabla

    @Column(name = "delivery_price", precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal deliveryPrice;

    @Column(name = "formula")
    @ColumnDefault("false")
    private Boolean formula; // Para calcular el total de costo-inversion del producto (Expense), y asi se calcula para deterninar el precio del mismo

    @Column(name = "active")
    @ColumnDefault("true")
    private Boolean active; // Si el producto es activo o no

    @Column(name = "creation_date", nullable = false)
    @ColumnDefault("now()")
    private LocalDateTime creationDate; // Fecha de creation

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Account creator; // Usuario que creó el producto

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentMeasure> assignmentMeasureList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentCategory> assignmentCategoryList;

}