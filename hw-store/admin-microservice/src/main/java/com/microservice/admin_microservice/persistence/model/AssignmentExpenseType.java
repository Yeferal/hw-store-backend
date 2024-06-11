package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_expense_type")
public class AssignmentExpenseType {
    @Column(name = "equivalent_value", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal equivalentValue;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal price;

    @Id
    @ManyToOne
    @JoinColumn(name = "expense_id", referencedColumnName = "id")
    private Expense expense;

    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "expense_type_id", referencedColumnName = "id")
    private ExpenseType expenseType;

}