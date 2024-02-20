package com.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Set;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "passwords")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean opcionalMayusculas;
    private boolean opcionalNumeros;
    private boolean opcionalSignos;
    private int longitudMinima;
    private int longitudMaxima;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOpcionalMayusculas() {
        return opcionalMayusculas;
    }

    public void setOpcionalMayusculas(boolean opcionalMayusculas) {
        this.opcionalMayusculas = opcionalMayusculas;
    }

    public boolean isOpcionalNumeros() {
        return opcionalNumeros;
    }

    public void setOpcionalNumeros(boolean opcionalNumeros) {
        this.opcionalNumeros = opcionalNumeros;
    }

    public boolean isOpcionalSignos() {
        return opcionalSignos;
    }

    public void setOpcionalSignos(boolean opcionalSignos) {
        this.opcionalSignos = opcionalSignos;
    }

    public int getLongitudMinima() {
        return longitudMinima;
    }

    public void setLongitudMinima(int longitudMinima) {
        this.longitudMinima = longitudMinima;
    }

    public int getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(int longitudMaxima) {
        this.longitudMaxima = longitudMaxima;
    }
}
