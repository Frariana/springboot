package com.springboot.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
public class Login {
    @NotBlank(message="Campo requerido")
    private String email;
    @NotBlank(message="Campo requerido")
    private String password;

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
