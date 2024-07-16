package com.forohub.service;

import com.forohub.domain.topico.Topico;
import com.forohub.domain.topico.TopicoRepository;
import com.forohub.domain.usuario.Usuario;
import com.forohub.domain.usuario.UsuarioRepository;
import com.forohub.dto.reply.RespuestaCreadaDTO;
import com.forohub.dto.reply.RespuestaDTO;
import com.forohub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public RespuestaCreadaDTO respuestaCreadaDTO(RespuestaDTO respuestaDTO) throws ValidacionDeIntegridad {
        // Verificar si el usuario existe en la base de datos
        Usuario usuario = usuarioRepository.findById(respuestaDTO.getUsuario_Id())
                .orElseThrow(() -> new ValidacionDeIntegridad("Este ID de usuario no está registrado en la base de datos."));

        // Verificar si el tópico existe en la base de datos
        Topico topico = topicoRepository.findById(respuestaDTO.getTopico_Id())
                .orElseThrow(() -> new ValidacionDeIntegridad("Este ID de tópico no está registrado en la base de datos."));

        // Crear la respuesta verificada y guardarla en el repositorio
        Respuesta respuestaVerificada = new Respuesta(null,
                respuestaDTO.getSolution(),
                usuario,
                topico,
                respuestaDTO.getCreationDate());

        Respuesta respuestaGuardada = respuestaRepository.save(respuestaVerificada);

        // Retornar el DTO de la respuesta creada
        return new RespuestaCreadaDTO(respuestaGuardada);
    }
}
