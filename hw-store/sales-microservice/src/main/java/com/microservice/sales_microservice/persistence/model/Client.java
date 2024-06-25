package com.microservice.sales_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "address", nullable = true, columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number", nullable = true)
    private String phone;

}