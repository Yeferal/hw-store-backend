package com.microservice.admin_microservice.persistence.model;

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
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 4)
    private BigDecimal amount; // Cantidad del producto

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description; // Description si es necesario

    @Column(name = "unit_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal unitPrice; // Precio unitario del producto

    @Column(name = "subtotal", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal subtotal; // Subtotal = amount * unitPrice

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

}