package com.springboot.service;

import com.springboot.error.EmailExistentException;
import com.springboot.model.User;
import com.springboot.model.Login;
import com.springboot.model.Password;
import com.springboot.repository.UserRepository;
import com.springboot.service.PasswordService;
import com.springboot.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Email;
import com.springboot.jwt.Jwt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.Date;

@Component
public class UserService {

    Jwt jwt = new Jwt();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;

    public User create(User user) throws EmailExistentException {
        List<User> usersExistentes = userRepository.findAll();
        for (User userAct : usersExistentes) { //Validar mail existente
            if (user.getEmail().equalsIgnoreCase(userAct.getEmail())) {
                throw new EmailExistentException("El correo ya está registrado");
            }
        }
        //Validar mail expresion regular
        String patron = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pat = Pattern.compile(patron);
        if (!pat.matcher(user.getEmail()).matches()){
            throw new EmailExistentException("Email tiene formato incorrecto, debe ser: aaaaaaa@dominio.cl");
        }
        //Validar password con formato
        if (!passwordService.validar(user.getPassword())){
            throw new EmailExistentException("Email tiene formato incorrecto, debe ser: aaaaaaa@dominio.cl");
        }
        return userRepository.save(user);
    }

    public String login(Login login){
        List<User> usersExistentes = userRepository.findAll();
        String token = "";
        for (User userAct : usersExistentes) {
            if (login.getEmail().equalsIgnoreCase(userAct.getEmail()) && userAct.getPassword().equals(login.getPassword())) {
                token = jwt.getJwt(login.getEmail());
                userAct.setLast_login(new Date());
                userAct.setToken(token);
                userRepository.save(userAct);
                return token;
            }
        }
        return token;
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
