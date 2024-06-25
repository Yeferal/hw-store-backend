package com.microservice.sales_microservice.persistence.model;

import com.microservice.sales_microservice.persistence.model.composite.AssignmentCategoryId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_category")
@IdClass(AssignmentCategoryId.class)
public class AssignmentCategory {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Category category;
}