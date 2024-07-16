package com.forohub.controller;

import com.forohub.dto.user.UserRegistrationDTO;
import com.forohub.dto.user.UserResponseDTO;
import com.forohub.repository.UserRepository;
import com.forohub.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            UserRegistrationDTO user = userService.registerUser(userRegistrationDTO);
            UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName());
            URI url = uriComponentsBuilder.path("user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(url).body(userResponseDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        }
    }
}
