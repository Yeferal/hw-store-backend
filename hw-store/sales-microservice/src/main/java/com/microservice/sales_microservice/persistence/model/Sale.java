package com.microservice.sales_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT DEFAULT 'Paqui, Totonicapan'")
    private String address;

    @Column(name = "phone_number", nullable = true)
    private String phone;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "total", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal total;

    @Column(name = "adviser", nullable = true, columnDefinition = "TEXT")
    private String adviser; // Asesor

    @Column(name = "pending_payment", nullable = false)
    @ColumnDefault("false")
    private Boolean pendingPayment; // Si es un pendiente de pago,

    @Column(name = "payment", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal payment; //Es es el abono que se hace en caso de no pagar todo

    @Column(name = "comment", nullable = true, columnDefinition = "TEXT")
    private String comment; // Algun commentario sobre la venta

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //Cuenta que realizo la venta
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}