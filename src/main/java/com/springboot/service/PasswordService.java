package com.springboot.service;

import com.springboot.error.EmailExistentException;
import com.springboot.model.Password;
import com.springboot.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public List<Password> getAll() {
        return passwordRepository.findAll();
    }
    public Password create(Password password) {
        return passwordRepository.save(password);
    }
    public Password update(Password password) {
        passwordRepository.deleteAll();
        return passwordRepository.save(password);
    }
    public boolean existeFormatoCreado(){
        List<Password> passwords = passwordRepository.findAll();
        int cantidad = passwords.size();
        if (cantidad>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean validar(String clave) throws EmailExistentException {
        Password f = passwordRepository.findAll().get(0);
        String patron = "";
        if (!f.isOpcionalMayusculas() && !f.isOpcionalNumeros() && !f.isOpcionalSignos()){//1
            patron = "[a-z]";
        }
        if (f.isOpcionalMayusculas() && !f.isOpcionalNumeros() && !f.isOpcionalSignos()){//2
            patron = "[A-Z+a-z]";
        }
        if (!f.isOpcionalMayusculas() && f.isOpcionalNumeros() && !f.isOpcionalSignos()){//3
            patron = "[a-z0-9]";
        }
        if (!f.isOpcionalMayusculas() && !f.isOpcionalNumeros() && f.isOpcionalSignos()){//4
            patron = "[a-z.,\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]";
        }
        if (f.isOpcionalMayusculas() && f.isOpcionalNumeros() && !f.isOpcionalSignos()){//5
            patron = "[A-Za-z0-9]";
        }
        if (!f.isOpcionalMayusculas() && f.isOpcionalNumeros() && f.isOpcionalSignos()){//6
            patron = "[a-z0-9.,\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]";
        }
        if (f.isOpcionalMayusculas() && !f.isOpcionalNumeros() && f.isOpcionalSignos()){//7
            patron = "[A-Za-z.,\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]";
        }
        if (f.isOpcionalMayusculas() && f.isOpcionalNumeros() && f.isOpcionalSignos()){//8
            patron = "[A-Za-z0-9.,\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]";
        }
        patron += "{"+f.getLongitudMinima()+","+f.getLongitudMaxima()+"}";
        System.out.println(patron);
        Pattern pat = Pattern.compile(patron);
        if (!pat.matcher(clave).matches()){
            throw new EmailExistentException("Formato de password incorrecto");
        }
        return true;
    }
}
