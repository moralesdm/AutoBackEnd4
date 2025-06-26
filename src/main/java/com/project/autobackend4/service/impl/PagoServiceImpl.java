package com.project.autobackend4.service.impl;

import com.project.autobackend4.entity.MetodoPago;
import com.project.autobackend4.entity.Pago;
import com.project.autobackend4.entity.Reserva;
import com.project.autobackend4.entity.dto.PagoRequest;
import com.project.autobackend4.entity.dto.PagoResponse;
import com.project.autobackend4.entity.usuario;
import com.project.autobackend4.repository.PagoRepository;
import com.project.autobackend4.repository.ReservaRepository;
import com.project.autobackend4.repository.UsuarioRepository;
import com.project.autobackend4.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public PagoResponse procesarPago(PagoRequest request) {
        usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Reserva reserva = reservaRepository.findById(request.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        MetodoPago metodo = MetodoPago.valueOf(request.getMetodoPago().toUpperCase());

        Pago pago = Pago.builder()
                .fecha(LocalDateTime.now())
                .monto(request.getMonto())
                .metodoPago(metodo)
                .usuario(usuario)
                .reserva(reserva)
                .build();

        return mapToResponse(pagoRepository.save(pago));
    }

    @Override
    public List<PagoResponse> pagosPorUsuario(Long usuarioId) {
        return pagoRepository.findByUsuarioId(usuarioId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<PagoResponse> pagosPorReserva(Long reservaId) {
        return pagoRepository.findByReservaId(reservaId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private PagoResponse mapToResponse(Pago pago) {
        return PagoResponse.builder()
                .id(pago.getId())
                .fecha(pago.getFecha())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago().name())
                .usuarioId(pago.getUsuario().getId())
                .reservaId(pago.getReserva().getId())
                .build();
    }
}

