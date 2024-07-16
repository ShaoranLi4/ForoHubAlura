package com.forohub.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegistroTopicoDTO {
    @NotBlank(message = "Título es obligatorio")
    private String title;

    @NotBlank(message = "Mensaje es obligatorio")
    private String message;

    @NotBlank(message = "Curso es obligatorio")
    private String course;

    @NotNull(message = "Author_id es obligatorio")
    private Long author_id;

    public RegistroTopicoDTO() {
    }

    public RegistroTopicoDTO(String title, String message, String course, Long author_id) {
        this.title = title;
        this.message = message;
        this.course = course;
        this.author_id = author_id;
    }

    // Getters y Setters
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }
}
