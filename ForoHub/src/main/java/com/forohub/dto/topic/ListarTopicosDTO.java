package com.forohub.dto.topic;

import java.time.LocalDateTime;

public class ListarTopicosDTO {
    private Long id;
    private String title;
    private String message;
    private Status status;
    private Long usuario_Id;
    private String curso;
    private LocalDateTime date;

    public ListarTopicosDTO(Long id, String title, String message, Status status, Long usuario_Id, String curso, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.status = status;
        this.usuario_Id = usuario_Id;
        this.curso = curso;
        this.date = date;
    }

    public ListarTopicosDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getAuthor().getId(),
                topico.getCourse(),
                topico.getDate()
        );
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(Long usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
