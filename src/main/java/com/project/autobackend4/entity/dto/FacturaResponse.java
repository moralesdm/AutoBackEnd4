package com.project.autobackend4.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FacturaResponse {
    private Long id;
    private String numero;
    private LocalDateTime fechaEmision;
    private Double montoTotal;
    private Integer usuarioId;
    private Long reservaId;
}