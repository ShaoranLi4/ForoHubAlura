package com.forohub.service;

import com.forohub.domain.usuario.UsuarioRepository;
import com.forohub.dto.topic.RespuestaTopicoDTO;
import com.forohub.dto.topic.Topico;
import com.forohub.infra.errors.ValidacionDeIntegridad;
import com.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaTopicoDTO topicoCreado(TopicoDTO topicoDTO) {
        // Verificar si el usuario existe
        if (!usuarioRepository.findById(topicoDTO.getUsuario_Id()).isPresent()) {
            throw new ValidacionDeIntegridad("El ID de usuario no está registrado en la base de datos.");
        }

        // Verificar si el título ya existe
        String title = topicoDTO.getTitle();
        if (title != null && topicoRepository.existsByTitleIgnoreCase(title)) {
            throw new ValidacionDeIntegridad("Este título ya está presente en la base de datos. Por favor revise el tópico existente.");
        }

        // Verificar si el mensaje ya existe
        String message = topicoDTO.getMessage();
        if (message != null && topicoRepository.existsByMessageIgnoreCase(message)) {
            throw new ValidacionDeIntegridad("Este mensaje ya está presente en la base de datos. Por favor revise el tópico existente.");
        }

        // Obtener el usuario
        var usuario = usuarioRepository.findById(topicoDTO.getUsuario_Id()).get();

        // Crear el objeto Topico
        var topico = new Topico(
                null,
                topicoDTO.getTitle(),
                topicoDTO.getMessage(),
                LocalDateTime.now(), // Suponiendo que la fecha actual se establece aquí
                topicoDTO.getStatus(),
                usuario,
                topicoDTO.getCurso()
        );

        // Guardar el tópico en el repositorio
        topicoRepository.save(topico);

        // Devolver DTO de respuesta
        return new RespuestaTopicoDTO(topico);
    }
}
