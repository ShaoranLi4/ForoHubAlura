package com.forohub.controller;

import com.forohub.dto.topico.*;
import com.forohub.dto.user.UsuarioRepository;
import com.forohub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/topico")
    @Transactional
    public ResponseEntity<?> topicoRegistrado(@RequestBody @Valid TopicoDTO topicoDTO) throws ValidacionDeIntegridad {
        RespuestaTopicoDTO topicoRegistrado = topicoService.registrarTopico(topicoDTO);
        return ResponseEntity.ok(topicoRegistrado);
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<ListarTopicosDTO>> listarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        Page<ListarTopicosDTO> topicos = topicoRepository.findByActiveTrue(pageable).map(ListarTopicosDTO::new);
        return ResponseEntity.ok(topicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> topicoActualizado(@PathVariable Long id, @RequestBody @Valid TopicoActualizadoDTO topicoActualizadoDTO) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID: " + id));

        topico.actualizarTopico(topicoActualizadoDTO);

        RespuestaTopicoDTO respuestaTopicoDTO = new RespuestaTopicoDTO(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getAuthor().getId(),
                topico.getCourse(),
                topico.getDate()
        );

        return ResponseEntity.ok(respuestaTopicoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID: " + id));

        topico.desactivarTopico();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID: " + id));

        RespuestaTopicoDTO respuestaTopicoDTO = new RespuestaTopicoDTO(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getAuthor().getId(),
                topico.getCourse(),
                topico.getDate()
        );

        return ResponseEntity.ok(respuestaTopicoDTO);
    }
}
