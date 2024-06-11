package com.microservice.admin_microservice.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "address", nullable = true, columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number", nullable = true)
    private String phone;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "total", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal total;

    @Column(name = "delivered_quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal delivered_quantity; //CUANTO EN PESO, CUANTAS LIBRAS O QUINTALES

    @Column(name = "delivered", nullable = true)
    @ColumnDefault("false")
    private Boolean delivered; // Si ya fue entregado o no

    @Column(name = "state", nullable = true)
    @ColumnDefault("false")
    private Boolean state; // Estado esto es para cuando se encesite eliminar o algo por lo parecido

    @Column(name = "pending_payment", nullable = false)
    @ColumnDefault("false")
    private Boolean pendingPayment; // Cuando lo hacen por abonos, si esta cancelado es true, si no es false

    @Column(name = "payment", nullable = false, precision = 10, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal payment; // La Cantidad que a pagado. es decir esta tiene que ser igual al total

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}