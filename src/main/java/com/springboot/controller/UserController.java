package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.model.Login;
import com.springboot.model.Password;
import com.springboot.jwt.Jwt;
import com.springboot.model.UserResponse;
import com.springboot.service.UserService;
import com.springboot.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.error.UserNotFoundException;
import com.springboot.error.EmailExistentException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Date;
import java.lang.String;
import java.lang.Object;

@RestController
@RequestMapping("api/users")
public class UserController {

    Jwt jwt = new Jwt();

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;

    @GetMapping("/vertodos")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User searchUserById(@PathVariable("id") UUID id) throws UserNotFoundException{
        return userService.getUserById(id);
    }

    @PostMapping
    public Map<String, Object> createUser(@Valid @RequestBody User user) throws EmailExistentException{
        Map<String, Object> map = new HashMap<>();
        if (passwordService.existeFormatoCreado()){
            user.setIsactive(true);
            user.setCreated(new Date());
            User usuario_creado = userService.create(user);
            UserResponse res = new UserResponse();
            res.setId(usuario_creado.getId_user().toString());
            res.setCreated(usuario_creado.getCreated());
            res.setToken(usuario_creado.getToken());
            res.setIsactive(usuario_creado.isIsactive());
            res.setToken(usuario_creado.getToken());
            map.put("user", res);
        }else{
            map.put("message", "No existe formato creado de password, debe configurar para permitir la creación de ususarios");
        }
        return map;
    }

    @PostMapping("/password")
    public Map<String, Object> createPassword(@Valid @RequestBody Password password){
        Map<String, Object> json = new HashMap<>();
        if (!passwordService.existeFormatoCreado()) {
            passwordService.create(password);
        }else{
            passwordService.update(password);
        }
        json.put("message", "Formato password creado");
        return json;
    }

    @GetMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody Login login){
        Map<String, Object> json = new HashMap<>();
        String token = userService.login(login);
        if (!token.isEmpty()){
            json.put("email", login.getEmail());
            json.put("token", token);
        }else{
            json.put("error", "Usuario o contraseña incorrectos");
        }
        return json;
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUser(id);
    }

    @GetMapping("/listar_users/{token}")
    public Map<String, Object> get(@PathVariable("token") String token) {
        Map<String, Object> map = new HashMap<>();
        boolean estado = jwt.verificarJwt(token);
        if (jwt.verificarJwt(token)){
            map.put("Users", userService.getAllUsers());
        }else{
            map.put("Estado token", "fail");
        }
        return map;
    }
}
