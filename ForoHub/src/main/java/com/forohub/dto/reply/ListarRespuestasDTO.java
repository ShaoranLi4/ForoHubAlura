package com.forohub.dto.reply;

import java.time.LocalDateTime;

public class ListarRespuestasDTO {
    private Long id;
    private String solution;
    private Long usuario_Id;
    private Long topico_Id;
    private LocalDateTime creationDate;

    public ListarRespuestasDTO(Long id, String solution, Long usuario_Id, Long topico_Id, LocalDateTime creationDate) {
        this.id = id;
        this.solution = solution;
        this.usuario_Id = usuario_Id;
        this.topico_Id = topico_Id;
        this.creationDate = creationDate;
    }

    public ListarRespuestasDTO(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate());
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Long getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(Long usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public Long getTopico_Id() {
        return topico_Id;
    }

    public void setTopico_Id(Long topico_Id) {
        this.topico_Id = topico_Id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
