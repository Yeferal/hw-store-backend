package com.microservice.sales_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_profile")
public class UserProfile {
    @Id
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = true, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "email", nullable = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId // Hace que el account_id tenga que existe obligatoriamente en Account id
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;
}