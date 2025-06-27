package com.project.autobackend4.service.impl;

import com.project.autobackend4.entity.Factura;
import com.project.autobackend4.entity.Pago;
import com.project.autobackend4.entity.Reserva;
import com.project.autobackend4.entity.dto.FacturaResponse;
import com.project.autobackend4.entity.usuario;
import com.project.autobackend4.repository.FacturaRepository;
import com.project.autobackend4.repository.PagoRepository;
import com.project.autobackend4.repository.ReservaRepository;
import com.project.autobackend4.repository.UsuarioRepository;
import com.project.autobackend4.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoRepository pagoRepository;

    @Override
    public List<FacturaResponse> listar() {
        return facturaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaResponse obtener(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return mapToResponse(factura);
    }

    @Override
    public FacturaResponse generarDesdeReserva(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        usuario usuario = reserva.getUsuario();

        List<Pago> pagos = pagoRepository.findByReservaId(reservaId);
        double total = pagos.stream().mapToDouble(Pago::getMonto).sum();

        Factura factura = Factura.builder()
                .numero("FAC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .fechaEmision(LocalDateTime.now())
                .montoTotal(total)
                .usuario(usuario)
                .reserva(reserva)
                .build();

        return mapToResponse(facturaRepository.save(factura));
    }

    private FacturaResponse mapToResponse(Factura factura) {
        return FacturaResponse.builder()
                .id(factura.getId())
                .numero(factura.getNumero())
                .fechaEmision(factura.getFechaEmision())
                .montoTotal(factura.getMontoTotal())
                .usuarioId(factura.getUsuario().getId())
                .reservaId(factura.getReserva().getId())
                .build();
    }
}