package com.microservice.inventory_microservice.persistence.model;

import com.microservice.inventory_microservice.persistence.model.composite.AssignmentMeasureId;
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
@Table(name = "assignment_measure")
@IdClass(AssignmentMeasureId.class)
public class AssignmentMeasure {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "measurement_unit_id")
    private Long measurementUnitId;

    @Column(name = "equivalent_value", nullable = false, precision = 20, scale = 4)
    @ColumnDefault("0.0000")
    private BigDecimal equivalentValue; // Valor equivalente a la dimension

    @Column(name = "price", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal price; // Precio equivalente en la dimension

    @Column(name = "is_base", nullable = false)
    @ColumnDefault("false")
    private Boolean isBase; // SI ESTA ASIGNAICON ES BASE DEL PRODUCTO O NO

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MeasurementUnit measurementUnit;
}