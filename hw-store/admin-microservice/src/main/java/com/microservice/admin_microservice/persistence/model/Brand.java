package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brands")
public class Brand {
    //Esta entidad es para los productos, la marca de cada producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "state", nullable = false)
    @ColumnDefault("true")
    private Boolean state; // Si la marca es activa o no

    @Column(name = "img_path", nullable = true, columnDefinition = "TEXT")
    private String imgPath; // Ruta de la imagen

//    @Column(nullable = false)
//    @ColumnDefault("true")
//    private Boolean active;

}