package com.microservice.admin_microservice.persistence.model.composite;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentRoleId implements Serializable {
    private Long accountId;
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentRoleId that = (AssignmentRoleId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }
}
