package com.forohub.controller;

import com.forohub.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.dto.Page;
import org.springframework.data.dto.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**************************************
     * REST API GET
     * Obtener todos los Usuarios
     * ENDPOINT :
     * http://localhost:8080/usuario/usuarios
     ***************************************/
    @GetMapping("/usuarios")
    public ResponseEntity<Page<ListarUsuariosDTO>> listarUsuarios(@PageableDefault(size = 10) Pageable pageable) {
        Page<ListarUsuariosDTO> usuarios = usuarioRepository.findByActiveTrue(pageable).map(ListarUsuariosDTO::new);
        return ResponseEntity.ok(usuarios);
    }

    /************************************************
     * REST API PUT
     * Actualizar un usuario por id
     * ENDPOINT :
     * http://localhost:8080/usuario/{id}
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizacionUsuario(@PathVariable Long id, @RequestBody @Valid ActualizacionUsuarioDTO actualizacionUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.actualizarUsuario(actualizacionUsuarioDTO);

        ActualizacionUsuarioDTO respuestaUsuarioDTO = new ActualizacionUsuarioDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail()
        );

        return ResponseEntity.ok(respuestaUsuarioDTO);
    }

    /************************************************
     * REST API DELETE
     * Eliminar un usuario por id
     * ENDPOINT :
     * http://localhost:8080/usuario/{id}
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.desactivarUsuario();

        return ResponseEntity.noContent().build();
    }

    /*******************************************
     * REST API GET
     * Obtener un Usuario por id
     * ENDPOINT :
     * http://localhost:8080/usuario/{id}
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        RespuestaUsuarioDTO respuestaUsuarioDTO = new RespuestaUsuarioDTO(
                usuario.getId(),
                usuario.getName()
        );

        return ResponseEntity.ok(respuestaUsuarioDTO);
    }
}
