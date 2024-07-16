package com.forohub.dto.topic;

import com.forohub.dto.user.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Usuario author;

    private String course;
    private boolean active;

    public Topico(Long id, String title, String message, LocalDateTime date, Status status, Usuario author, String course) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.date = date;
        this.status = status;
        this.author = author;
        this.course = course;
        this.active = true; // Por defecto, un nuevo tópico está activo
    }

    public void topicoActualizado(TopicoActualizadoDTO topicoActualizadoDTO) {
        if (topicoActualizadoDTO.getTitle() != null) {
            this.title = topicoActualizadoDTO.getTitle();
        }
        if (topicoActualizadoDTO.getMessage() != null) {
            this.message = topicoActualizadoDTO.getMessage();
        }
        if (topicoActualizadoDTO.getStatus() != null) {
            this.status = topicoActualizadoDTO.getStatus();
        }
        if (topicoActualizadoDTO.getCurso() != null) {
            this.course = topicoActualizadoDTO.getCurso();
        }
    }

    public void diactivateTopic() {
        this.active = false;
    }
}
