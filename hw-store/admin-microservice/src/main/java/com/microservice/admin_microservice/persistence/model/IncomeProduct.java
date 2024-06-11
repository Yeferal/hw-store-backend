package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "income_products")
public class IncomeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "total", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}