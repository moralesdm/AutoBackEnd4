package com.project.autobackend4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private Double monto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne(optional = false)
    private usuario usuario;

    @ManyToOne(optional = false)
    private Reserva reserva;
}
