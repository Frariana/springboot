package com.springboot.model;

import com.springboot.model.User;
import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "phones")
public class Phone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_phone;
    private int number;
    private int citycode;
    private int contrycode;

    public Long getId_phone() {
        return id_phone;
    }

    public void setId_phone(Long id_phone) {
        this.id_phone = id_phone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }

    public int getContrycode() {
        return contrycode;
    }

    public void setContrycode(int contrycode) {
        this.contrycode = contrycode;
    }
}
