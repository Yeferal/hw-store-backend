package com.microservice.inventory_microservice.persistence.model.composite;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentMeasureId implements Serializable {

    private Long productId;
    private Long measurementUnitId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentMeasureId that = (AssignmentMeasureId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(measurementUnitId, that.measurementUnitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, measurementUnitId);
    }

}
