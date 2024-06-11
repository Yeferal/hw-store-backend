package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_category")
public class AssignmentCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}