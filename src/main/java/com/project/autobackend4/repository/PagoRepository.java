package com.project.autobackend4.repository;

import com.project.autobackend4.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuarioId);
    List<Pago> findByReservaId(Long reservaId);
}