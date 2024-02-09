package com.springboot.service;

import com.springboot.error.EmailExistentException;
import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import com.springboot.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Email;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) throws EmailExistentException {
        List<User> usersExistentes = userRepository.findAll();
        //Validar mail existente
        for (User userAct : usersExistentes) {
            if (user.getEmail().equalsIgnoreCase(userAct.getEmail())) {
                throw new EmailExistentException("El correo ya está registrado");
            }
        }
        //Validar mail expresion regular
        Pattern pat = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        if (!pat.matcher(user.getEmail()).matches()){
            throw new EmailExistentException("Email tiene formato incorrecto, debe ser: aaaaaaa@dominio.cl");
        }
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
