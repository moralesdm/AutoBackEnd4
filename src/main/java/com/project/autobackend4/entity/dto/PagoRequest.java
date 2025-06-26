package com.project.autobackend4.entity.dto;

import lombok.Data;

@Data
public class PagoRequest {
    private Integer usuarioId;
    private Long reservaId;
    private Double monto;
    private String metodoPago; // TARJETA, EFECTIVO, TRANSFERENCIA
}