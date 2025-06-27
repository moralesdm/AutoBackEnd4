package com.project.autobackend4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private LocalDateTime fechaEmision;

    private Double montoTotal;

    @ManyToOne(optional = false)
    private usuario usuario;

    @OneToOne(optional = false)
    private Reserva reserva;
}