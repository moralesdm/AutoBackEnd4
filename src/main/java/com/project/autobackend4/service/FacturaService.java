package com.project.autobackend4.service;


import com.project.autobackend4.entity.dto.FacturaResponse;

import java.util.List;

public interface FacturaService {
    List<FacturaResponse> listar();
    FacturaResponse obtener(Long id);
    FacturaResponse generarDesdeReserva(Long reservaId);
}
