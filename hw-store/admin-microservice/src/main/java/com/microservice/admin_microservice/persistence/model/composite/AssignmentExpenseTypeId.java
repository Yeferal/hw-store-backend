package com.microservice.admin_microservice.persistence.model.composite;

import com.microservice.admin_microservice.persistence.model.Expense;
import com.microservice.admin_microservice.persistence.model.ExpenseType;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentExpenseTypeId implements Serializable {

    private Expense expense;
    private ExpenseType expenseType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentExpenseTypeId that = (AssignmentExpenseTypeId) o;
        return Objects.equals(expense, that.expense) &&
                Objects.equals(expenseType, that.expenseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expense, expenseType);
    }
}
