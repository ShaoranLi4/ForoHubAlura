package com.forohub.repository;

import api.hub.dto.user.RegistroUsuarioDTO;

public interface ValidadorDeUsuario {
    void validate(RegistroUsuarioDTO registroUsuarioDTO);
}
