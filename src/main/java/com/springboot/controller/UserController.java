package com.springboot.controller;
import java.util.List;
import com.springboot.model.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.error.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

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
    public User createUser(@Valid @RequestBody User user){
        return userService.create(user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUser(id);
    }

    @GetMapping("/test")
        public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("results", "somePOJO");
        return map;
    }
}
