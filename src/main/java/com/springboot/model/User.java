package com.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
//import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message="Nombre es un campo requerido")
    private String name;
    private String email;
//    private String password;
//    // private ArrayList<Phone> phones;
//    private Date created;
//    private Date modified;
//    private Date last_login;
//    private String token;
//    private boolean isactive;



//    public User() {
//        this.phones = new ArrayList<>();
//    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    public ArrayList<Phone> getPhones() {
////        return phones;
////    }
//
////    public void setPhones(ArrayList<Phone> phones) {
////        this.phones = phones;
////    }
////
////    public void addPhone(Phone phone) {
////        this.phones.add(phone);
////    }
//
//    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//
//    public Date getModified() {
//        return modified;
//    }
//
//    public void setModified(Date modified) {
//        this.modified = modified;
//    }
//
//    public Date getLast_login() {
//        return last_login;
//    }
//
//    public void setLast_login(Date last_login) {
//        this.last_login = last_login;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public boolean isIsactive() {
//        return isactive;
//    }
//
//    public void setIsactive(boolean isactive) {
//        this.isactive = isactive;
//    }
}
