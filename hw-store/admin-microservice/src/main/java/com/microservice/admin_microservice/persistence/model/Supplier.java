package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = true, columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number_1", nullable = true)
    private String tel1;

    @Column(name = "phone_number_2", nullable = true)
    private String tel2;

    @OneToMany(mappedBy = "supplier")
    private List<ProductIncome> incomeProducts;

}