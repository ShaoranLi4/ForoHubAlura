package com.forohub.dto.user;

import javax.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
