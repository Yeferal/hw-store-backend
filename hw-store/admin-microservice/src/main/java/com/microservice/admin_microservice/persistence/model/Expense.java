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
@Table(name = "expenses")
public class Expense {
    // Gastos, todo los gatos que se pueden tener a nivel general, de toda la tienda
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name; // Nombre del Gasto, por ejemplo, luz, internet, cable, camaras, etc.

    @Column(name = "percentage", nullable = false)
    @ColumnDefault("false")
    private Boolean percentage; //0 ES AGREGADO, 1 ES PORCENTUAL AL COSTO

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal value; // El valor del gasto

}