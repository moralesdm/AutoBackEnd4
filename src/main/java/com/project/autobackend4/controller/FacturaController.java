package com.project.autobackend4.controller;

import com.project.autobackend4.entity.dto.FacturaResponse;
import com.project.autobackend4.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaResponse>> listar() {
        return ResponseEntity.ok(facturaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtener(id));
    }

    @PostMapping("/generar/{reservaId}")
    public ResponseEntity<FacturaResponse> generar(@PathVariable Long reservaId) {
        return ResponseEntity.status(201).body(facturaService.generarDesdeReserva(reservaId));
    }
}