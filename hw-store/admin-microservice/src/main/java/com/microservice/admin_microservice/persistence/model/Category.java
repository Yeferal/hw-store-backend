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
@Table(name = "categories")
public class Category {
    //Esta entidad es para los productos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "destacado", nullable = false)
    @ColumnDefault("false")
    private Boolean featured; // Si la categoria es destacada o no

    @Column(name = "state", nullable = false)
    @ColumnDefault("true")
    private Boolean state; // Si la categoria es activa o no

    @Column(name = "img_path", nullable = true, columnDefinition = "TEXT")
    private String imgPath; // Ruta de la imagen

}