package com.forohub.dto.reply;

import com.forohub.dto.topico.Topico;
import com.forohub.dto.user.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private String solution;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Usuario author;

    @ManyToOne
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private Topico topico;

    @Column(name = "active")
    private boolean active;

    public Respuesta(Long id, String solution, Usuario usuario, Topico topico, LocalDateTime creationDate) {
        this.id = id;
        this.solution = solution;
        this.author = usuario;
        this.topico = topico;
        this.creationDate = LocalDateTime.now();
    }

    public void respuestaActualizada(RespuestaActualizadaDTO respuestaActualizadaDTO) {
        if (respuestaActualizadaDTO.getSolution() != null) {
            this.solution = respuestaActualizadaDTO.getSolution();
        }
    }

    public void diactivateResponse() {
        this.active = false;
    }
}
