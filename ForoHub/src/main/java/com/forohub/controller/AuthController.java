package com.forohub.controller;

import com.forohub.dto.user.UserDTO;
import com.forohub.model.User;
import com.forohub.service.TokenService;
import com.forohub.dto.JWTTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity authenticateUser(@RequestBody @Valid UserDTO userDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
        var authResult = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) authResult.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(token));
    }
}
