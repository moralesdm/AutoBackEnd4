package com.project.autobackend4.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PagoResponse {
    private Long id;
    private LocalDateTime fecha;
    private Double monto;
    private String metodoPago;
    private Integer usuarioId;
    private Long reservaId;
}
