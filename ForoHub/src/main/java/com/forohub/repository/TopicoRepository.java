package com.forohub.repository;

import com.forohub.dto.topic.Topico;
import org.springframework.data.dto.Page;
import org.springframework.data.dto.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByMessageIgnoreCase(String message);
    Page<Topico> findByActiveTrue(Pageable paged);
}
