package com.springboot.controller;
import java.util.List;
import com.springboot.model.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User searchUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
