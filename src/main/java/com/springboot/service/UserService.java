package com.springboot.service;
import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.error.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User getUserById(UUID id) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Usuario no encontrado :C");
        }
        return optionalUser.get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

}
