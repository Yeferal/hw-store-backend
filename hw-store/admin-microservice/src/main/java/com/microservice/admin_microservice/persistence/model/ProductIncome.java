package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products_income")
public class ProductIncome {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remitted_by")
    private Account remitter; // Usuario que registro el ingreso, el que lo recibio

    @OneToMany(mappedBy = "productIncome", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases;

}