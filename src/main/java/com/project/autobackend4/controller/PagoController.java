package com.project.autobackend4.controller;

import com.project.autobackend4.entity.dto.PagoRequest;
import com.project.autobackend4.entity.dto.PagoResponse;
import com.project.autobackend4.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/procesar")
    public ResponseEntity<PagoResponse> procesar(@RequestBody PagoRequest request) {
        return ResponseEntity.status(201).body(pagoService.procesarPago(request));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PagoResponse>> porUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.pagosPorUsuario(id));
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<List<PagoResponse>> porReserva(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.pagosPorReserva(id));
    }
}


