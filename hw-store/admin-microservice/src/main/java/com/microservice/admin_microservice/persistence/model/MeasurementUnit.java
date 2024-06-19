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
@Table(name = "measurement_units")
public class MeasurementUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "magnitude", nullable = true)
    private String magnitude;

    @OneToMany(mappedBy = "measurementUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentMeasure> assignmentMeasureList;

}