package com.springboot.model;

import com.springboot.model.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_user;
    @NotBlank(message="Campo requerido")
    private String name;
    @NotBlank(message="Campo requerido")
    private String email;
    @NotBlank(message="Campo requerido")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();
    private Date created;
    private Date modified;
    private Date last_login;
    private String token;
    private boolean isactive;

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
