package com.microservice.auth_microservice.persistence.model;

import com.microservice.auth_microservice.persistence.model.composite.AssignmentRoleId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_role")
@IdClass(AssignmentRoleId.class)
public class AssignmentRole {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

}