package com.forohub.repository;

import org.springframework.data.dto.Page;
import org.springframework.data.dto.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findByActiveTrue(Pageable pageable);
}
