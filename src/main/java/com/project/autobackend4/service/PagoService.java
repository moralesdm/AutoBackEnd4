package com.project.autobackend4.service;


import com.project.autobackend4.entity.dto.PagoRequest;
import com.project.autobackend4.entity.dto.PagoResponse;

import java.util.List;

public interface PagoService {
    PagoResponse procesarPago(PagoRequest request);
    List<PagoResponse> pagosPorUsuario(Long usuarioId);
    List<PagoResponse> pagosPorReserva(Long reservaId);
}