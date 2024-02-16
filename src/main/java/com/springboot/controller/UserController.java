package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.model.Login;
import com.springboot.jwt.Jwt;
import com.springboot.model.UserResponse;
import com.springboot.service.UserService;
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

    @GetMapping("/vertodos")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User searchUserById(@PathVariable("id") UUID id) throws UserNotFoundException{
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody User user) throws EmailExistentException{
        user.setIsactive(true);
        user.setCreated(new Date());
        user.setToken(jwt.getJwt(user.getEmail()));
        UserResponse res = new UserResponse();
        userService.create(user);
        res.setId(user.getId_user().toString());
        res.setCreated(user.getCreated());
        res.setToken(user.getToken());
        res.setIsactive(user.isIsactive());
        res.setToken(user.getToken());
        return res;
    }

    @GetMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody Login login){
        Map<String, Object> json = new HashMap<>();
        if (userService.login(login)){
            json.put("email", login.getEmail());
            json.put("token", jwt.getJwt(login.getEmail()));
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
            map.put("Estado token", "fraude");
        }
        return map;
    }
}
