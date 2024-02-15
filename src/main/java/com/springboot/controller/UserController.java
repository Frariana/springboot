package com.springboot.controller;

import com.springboot.model.User;
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

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
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
        jwt.verificarJwt(user.getToken());
        return res;
    }



    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUser(id);
    }

    @GetMapping("/test")
        public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("results", "value2");
        return map;
    }
}
