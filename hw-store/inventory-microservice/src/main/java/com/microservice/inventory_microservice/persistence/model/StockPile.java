package com.microservice.inventory_microservice.persistence.model;

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
@Table(name = "stock_pile")
public class StockPile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal amount; // Cantidad en base al mesearu

    @Column(name = "purchase_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal purchasePrice; // Precio de Compra

//    @Column(name = "sale_price", nullable = false, precision = 20, scale = 2)
//    private BigDecimal salePrice; // Precio de Venta

    @Column(name = "active", nullable = false)
    @ColumnDefault("true")
    private Boolean active; // Si es true, es porque aun esta en estock, false es porque ya no

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}