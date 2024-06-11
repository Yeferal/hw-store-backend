package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "operating_costs")
public class OperateCost {
    // Gastos o costos de operacion, puramente de las ventas, trasporte, viaticos, bonos, etc
    //Diferente de Expense, que engloba los costos fijos, como la electricidad, etc.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal cost;

//    @Column(name = "operation_type", nullable = false)
//    private int operationType;

    @ManyToOne
    @JoinColumn(name = "operation_type_id")
    private OperationType operationType;

}