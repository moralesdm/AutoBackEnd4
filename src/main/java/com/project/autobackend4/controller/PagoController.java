package com.project.autobackend4.controller;

import com.project.autobackend4.entity.dto.PagoRequest;
import com.project.autobackend4.entity.dto.PagoResponse;
import com.project.autobackend4.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    // Crear un pago
    @PostMapping("/procesar")
    public ResponseEntity<PagoResponse> procesar(@RequestBody PagoRequest request) {
        return ResponseEntity.status(201).body(pagoService.procesarPago(request));
    }

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<PagoResponse>> obtenerTodos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    // Obtener pagos por usuario
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PagoResponse>> porUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.pagosPorUsuario(id));
    }

    // Obtener pagos por reserva
    @GetMapping("/reserva/{id}")
    public ResponseEntity<List<PagoResponse>> porReserva(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.pagosPorReserva(id));
    }

    // Actualizar un pago
    @PutMapping("/{id}")
    public ResponseEntity<PagoResponse> actualizarPago(@PathVariable Long id, @RequestBody PagoRequest request) {
        return ResponseEntity.ok(pagoService.actualizarPago(id, request));
    }

    // (Opcional) Eliminar un pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
